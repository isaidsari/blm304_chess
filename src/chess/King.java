package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class King extends Piece
{
    King(Color color)
    {
        super(color);
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

        // Check if the move is within the range of 1 square in any direction
        if (rowOffset <= 1 && colOffset <= 1) {
            Piece targetPiece = board.pieceAt(to);
            if (targetPiece == null || targetPiece.color != this.color) {
                return true;  // Valid move
            }
        }

        // If the move is outside the range or the target position is occupied by a piece of the same color, it is invalid
        return false;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        int[][] offsets = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
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
