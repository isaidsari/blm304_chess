package chess;

public class Square
{

    Piece piece;
    char column;
    int row;

    Square(char column, int row)
    {
        this.column = column;
        this.row = row;
    }

}
