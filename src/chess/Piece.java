package chess;

import java.awt.*;

abstract class Piece
{
    Color color;
    Piece(Color color)
    {
        this.color = color;
    }

}
//https://www.wbec-ridderkerk.nl/html/UCIProtocol.html
//https://www.chessprogramming.org/UCI
