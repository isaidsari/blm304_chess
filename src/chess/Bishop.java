package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Bishop extends Piece
{
    Bishop(Color color)
    {
        super(color);
    }

    @Override
    boolean canMove(Board board, Point from, Point to) {
        // Check if the source and target positions are the same
        if (from.equals(to)) {
            return false;
        }

        // Check if the move is along a diagonal
        int rowOffset = Math.abs(to.y - from.y);
        int colOffset = Math.abs(to.x - from.x);
        if (rowOffset != colOffset) {
            return false;
        }

        // Check if there are any obstacles in the diagonal path
        int rowDirection = (to.y > from.y) ? 1 : -1;
        int colDirection = (to.x > from.x) ? 1 : -1;
        int currentRow = from.y + rowDirection;
        int currentCol = from.x + colDirection;
        while (currentRow != to.y && currentCol != to.x) {
            if (board.pieceAt(new Point(currentRow, currentCol)) != null) {
                return false;  // Obstacle found
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // If all checks pass, the move is valid
        return true;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        int[][] directions = {
                {-1, -1}, {-1, 1},
                {1, -1}, {1, 1}
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
