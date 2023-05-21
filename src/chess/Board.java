package chess;

import java.awt.*;

public class Board
{

    public static final int SIZE = 8;

    Square[][] squares;

    public Board()
    {
        initializeBoard();
    }

    void initializeBoard()
    {
        squares = new Square[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++) {
                squares[i][j] = new Square((char) ('a' + i), j + 1);
                squares[i][j].piece = null;
            }


        squares[0][0].piece = new Rook(Color.BLACK);
        squares[1][0].piece = new Knight(Color.BLACK);
        squares[2][0].piece = new Bishop(Color.BLACK);
        squares[3][0].piece = new Queen(Color.BLACK);
        squares[4][0].piece = new King(Color.BLACK);
        squares[5][0].piece = new Bishop(Color.BLACK);
        squares[6][0].piece = new Knight(Color.BLACK);
        squares[7][0].piece = new Rook(Color.BLACK);
        for(int i = 0; i < SIZE; i++)
            squares[i][1].piece = new Pawn(Color.BLACK);

        squares[0][7].piece = new Rook(Color.WHITE);
        squares[1][7].piece = new Knight(Color.WHITE);
        squares[2][7].piece = new Bishop(Color.WHITE);
        squares[3][7].piece = new Queen(Color.WHITE);
        squares[4][7].piece = new King(Color.WHITE);
        squares[5][7].piece = new Bishop(Color.WHITE);
        squares[6][7].piece = new Knight(Color.WHITE);
        squares[7][7].piece = new Rook(Color.WHITE);
        for(int i = 0; i < SIZE; i++)
            squares[i][6].piece = new Pawn(Color.WHITE);
    }

    public Piece pieceAt(Point point)
    {
        return squares[point.x][point.y].piece;
    }

    public boolean move(Point from, Point to)
    {
        /*
        if (!squares[to.y][to.x].piece.canMove(this, from, to))
            return false;
        */

        System.out.println("moving from "+from+" to "+to);
        squares[to.x][to.y].piece = squares[from.x][from.y].piece;
        squares[from.x][from.y].piece = null;

        return true;
    }
}
