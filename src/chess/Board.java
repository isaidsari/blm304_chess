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
            for(int j = 0; j < SIZE; j++)
                squares[i][j] = new Square((char) ('a' + i), j + 1);

        squares[0][0].piece = new Rook(Color.BLACK);
        squares[0][1].piece = new Knight(Color.BLACK);
        squares[0][2].piece = new Bishop(Color.BLACK);
        squares[0][3].piece = new Queen(Color.BLACK);
        squares[0][4].piece = new King(Color.BLACK);
        squares[0][5].piece = new Bishop(Color.BLACK);
        squares[0][6].piece = new Knight(Color.BLACK);
        squares[0][7].piece = new Rook(Color.BLACK);
        for(int i = 0; i < SIZE; i++)
            squares[1][i].piece = new Pawn(Color.BLACK);

        squares[7][0].piece = new Rook(Color.WHITE);
        squares[7][1].piece = new Knight(Color.WHITE);
        squares[7][2].piece = new Bishop(Color.WHITE);
        squares[7][3].piece = new Queen(Color.WHITE);
        squares[7][4].piece = new King(Color.WHITE);
        squares[7][5].piece = new Bishop(Color.WHITE);
        squares[7][6].piece = new Knight(Color.WHITE);
        squares[7][7].piece = new Rook(Color.WHITE);
        for(int i = 0; i < SIZE; i++)
            squares[6][i].piece = new Pawn(Color.WHITE);
    }

    public Piece pieceAt(Point point)
    {
        return squares[point.y][point.x].piece;
    }

    void move(Point from, Point to)
    {
        squares[to.y][to.x].piece.canMove(this, from, to);
    }
}
