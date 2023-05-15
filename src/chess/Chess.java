package chess;

import java.awt.*;

public class Chess
{

    public Board board;

    public Color turn;

    public Chess()
    {
        board = new Board();
        turn = Color.WHITE;
    }

    public void move(Point from, Point to)
    {
        if (board.pieceAt(from).color == turn)
        {
            board.move(from, to);
            turn = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
    }



}
