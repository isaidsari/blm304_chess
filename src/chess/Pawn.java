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

        return true;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }
}
