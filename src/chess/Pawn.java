package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{

    boolean hasMoved = false;

    Pawn(Color color)
    {
        super(color);
        name = "Pawn";
    }

    @Override
    boolean canMove(Board board, Point from, Point to) {
        // Check if the source and target positions are the same
        if (from.equals(to)) {
            return false;
        }

        // Determine the direction of movement based on the piece's color
        int direction = (this.color.equals(Color.WHITE)) ? -1 : 1;

        // Check if the move is forward
        int rowOffset = to.y - from.y;
        int colOffset = Math.abs(to.x - from.x);
        if (rowOffset * direction <= 0 || colOffset > 1) {
            return false;
        }

        // Check for capturing an opponent's piece diagonally
        if (colOffset == 1 && rowOffset * direction == 1) {
            Piece targetPiece = board.pieceAt(to);
            if (targetPiece == null || targetPiece.color.equals(this.color)) {
                return false;
            }
        }

        // Check for regular forward movement
        if (colOffset == 0 && rowOffset * direction == 1) {
            if (board.pieceAt(to) != null) {
                return false;
            }
        }

        // Check if it is the first move of the pawn
        if (this.hasMoved && colOffset == 0 && rowOffset * direction == 2) {
            Point intermediatePosition = new Point(from.x, from.y + direction);
            if (board.pieceAt(intermediatePosition) != null) {
                return false;
            }
        }

        // If all checks pass, the move is valid
        return true;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        int direction = (this.color == Color.WHITE) ? -1 : 1;
        int startRow = (this.color == Color.WHITE) ? 6 : 1;

        // Check one square forward
        int destX = from.x;
        int destY = from.y + direction;
        Point destination = new Point(destX, destY);
        if (canMove(board, from, destination)) {
            possibleMoves.add(destination);
        }

        // Check two squares forward (if it's the pawn's first move)
        if (from.y == startRow) {
            destY = from.y + (2 * direction);
            destination = new Point(destX, destY);
            if (canMove(board, from, destination) && !isObstructed(board, from, destination)) {
                possibleMoves.add(destination);
            }
        }

        // Check diagonal capture moves
        int[] captureOffsets = {-1, 1};
        for (int offset : captureOffsets) {
            destX = from.x + offset;
            destY = from.y + direction;
            destination = new Point(destX, destY);
            if (canMove(board, from, destination) && board.pieceAt(destination) != null) {
                possibleMoves.add(destination);
            }
        }

        return possibleMoves;
    }
}
