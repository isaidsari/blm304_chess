package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Rook extends Piece
{
    Rook(Color color)
    {
        super(color);
    }

    @Override
    boolean canMove(Board board, Point from, Point to) {
        // Check if the source and target positions are the same
        if (from.equals(to)) {
            return false;
        }

        // Check if the move is along a row or column
        if (from.x == to.x || from.y == to.y) {
            // Check if there are any obstacles in the path
            int rowOffset = to.y - from.y;
            int colOffset = to.x - from.x;

            // Check if the move is along a row
            if (rowOffset == 0) {
                int colDirection = (colOffset > 0) ? 1 : -1;
                for (int col = from.x + colDirection; col != to.x; col += colDirection) {
                    if (board.pieceAt(new Point(col, from.y)) != null) {
                        return false;  // Obstacle found
                    }
                }
            }
            // Check if the move is along a column
            else if (colOffset == 0) {
                int rowDirection = (rowOffset > 0) ? 1 : -1;
                for (int row = from.y + rowDirection; row != to.y; row += rowDirection) {
                    if (board.pieceAt(new Point(from.x, row)) != null) {
                        return false;  // Obstacle found
                    }
                }
            }
            return true;  // Valid move
        }

        // If the move is neither along a row nor a column, it is invalid
        return false;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        int[][] directions = {
                {-1, 0}, {1, 0},
                {0, -1}, {0, 1}
        };

        for (int[] direction : directions) {
            int destX = from.x;
            int destY = from.y;

            while (true) {
                destX += direction[0];
                destY += direction[1];
                Point destination = new Point(destX, destY);

                if (canMove(board, from, destination)) {
                    possibleMoves.add(destination);
                }

                if (!isValidPosition(destination) || board.pieceAt(destination) != null) {
                    break;
                }
            }
        }

        return possibleMoves;
    }
}
