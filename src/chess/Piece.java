package chess;

import java.awt.*;
import java.util.List;

abstract class Piece
{
    Color color;
    Piece(Color color)
    {
        this.color = color;
    }

    boolean isObstructed(Board board, Point from, Point to) {
        int dx = Integer.compare(to.x, from.x);
        int dy = Integer.compare(to.y, from.y);

        int x = from.x + dx;
        int y = from.y + dy;

        while (x != to.x || y != to.y) {
            if (board.pieceAt(new Point(x, y)) != null) {
                return true; // Obstruction found
            }
            x += dx;
            y += dy;
        }

        return false; // No obstruction found
    }

    boolean isValidPosition(Point position) {
        int x = position.x;
        int y = position.y;

        // Check if the position falls within the valid bounds of the chessboard
        return x >= 0 && x < Board.SIZE && y >= 0 && y < Board.SIZE;
    }

    abstract boolean canMove(Board board, Point from, Point to);
    abstract List<Point> possibleMoves(Board board, Point from);

}
//https://www.wbec-ridderkerk.nl/html/UCIProtocol.html
//https://www.chessprogramming.org/UCI
