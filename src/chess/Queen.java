package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Queen extends Piece
{
    Queen(Color color)
    {
        super(color);
    }

    @Override
    boolean canMove(Board board, Point from, Point to) {
        // Check if the source and target positions are the same
        if (from.equals(to)) {
            return false;
        }

        // Check if the move is along a row, column, or diagonal
        int rowOffset = Math.abs(to.y - from.y);
        int colOffset = Math.abs(to.x - from.x);

        // Check if the move is along a row or a column
        if (from.x == to.x || from.y == to.y) {
            // Check if there are any obstacles in the path
            int direction = (from.y == to.y) ? (to.x - from.x) / colOffset : (to.y - from.y) / rowOffset;
            int row = from.y + direction;
            int col = from.x + direction;
            while (row != to.y || col != to.x) {
                if (board.pieceAt(new Point(col, row)) != null) {
                    return false;  // Obstacle found
                }
                row += direction;
                col += direction;
            }
            return true;  // Valid move
        }

        // Check if the move is along a diagonal
        if (rowOffset == colOffset) {
            // Check if there are any obstacles in the path
            int rowDirection = (to.y > from.y) ? 1 : -1;
            int colDirection = (to.x > from.x) ? 1 : -1;
            int row = from.y + rowDirection;
            int col = from.x + colDirection;
            while (row != to.y && col != to.x) {
                if (board.pieceAt(new Point(col, row)) != null) {
                    return false;  // Obstacle found
                }
                row += rowDirection;
                col += colDirection;
            }
            return true;  // Valid move
        }

        // If the move is neither along a row, column, nor diagonal, it is invalid
        return false;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
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
