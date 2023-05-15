package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Knight extends Piece
{
    Knight(Color color)
    {
        super(color);
        name = "Knight";
    }

    @Override
    boolean canMove(Board board, Point from, Point to) {
        // Check if the source and target positions are the same
        if (from.equals(to)) {
            return false;
        }

        // Calculate the absolute differences in row and column indices
        int rowOffset = Math.abs(to.y - from.y);
        int colOffset = Math.abs(to.x - from.x);

        // Check if the move is an L-shape (2 rows and 1 column or 1 row and 2 columns)
        if ((rowOffset == 2 && colOffset == 1) || (rowOffset == 1 && colOffset == 2)) {
            Piece targetPiece = board.pieceAt(to);
            if (targetPiece == null || !targetPiece.color.equals(this.color)) {
                return true;  // Valid move
            }
        }

        // If the L-shape condition is not met, the move is invalid
        return false;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        int[][] offsets = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };

        for (int[] offset : offsets) {
            int destX = from.x + offset[0];
            int destY = from.y + offset[1];
            Point destination = new Point(destX, destY);

            if (canMove(board, from, destination)) {
                possibleMoves.add(destination);
            }
        }

        return possibleMoves;
    }
}
