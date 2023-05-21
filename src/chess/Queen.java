package chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece
{
    Queen(Color color)
    {
        super(color);
        name = "Queen";
    }

    @Override
    boolean canMove(Board board, Point from, Point to) {
        // Check if the source and target positions are the same
        if (from.equals(to)) {
            return false;
        }

        return false;
    }

    @Override
    List<Point> possibleMoves(Board board, Point from) {
        List<Point> possibleMoves = new ArrayList<>();

        return possibleMoves;
    }
}
