package client;

import chess.Board;
import chess.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    Client client;

    public MainFrame(Client client) {
        this.client = client;
        initComponents();
        setSize(420, 440);
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess");
        //setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        );

        // draw the board

        BoardPanel boardPanel = new BoardPanel(client);
        boardPanel.setBounds(0, 0, 400, 400);
        this.add(boardPanel);

        pack();
    }
}


class BoardPanel extends JPanel {

    Client client;
    Square[][] squares;

    public BoardPanel(Client client) {
        this.client = client;
        initComponents();

    }

    private void initComponents() {

        setLayout(null);

        squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j, (i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
                square.setBounds(i * 50, j * 50, 50, 50);
                this.add(square);
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        client.clickSquare(square.x, square.y);
                    }
                });
                squares[i][j] = square;
            }
        }

        // draw ranks and files
        for (int i = 0; i < 8; i++) {
            JLabel fileLabel = new JLabel(Character.toString((char) (i + 97)));
            fileLabel.setBounds(i * 50 + 25, 400, 50, 50);
            this.add(fileLabel);

            JLabel rankLabel = new JLabel(Integer.toString(8 - i));
            rankLabel.setBounds(400, i * 50 + 25, 50, 50);
            this.add(rankLabel);
        }

        drawBoard(client.board);
    }

    void drawBoard(Board board) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {

                Piece piece = board.pieceAt(new Point(i, j));
                String color = "";

                if (board.pieceAt(new Point(i, j)) != null) {
                    if (piece.color == Color.WHITE) {
                        color = "white";
                    } else if (piece.color == Color.BLACK){
                        color = "black";
                    }
                    squares[i][j].setImage("src\\client\\images\\" + piece.name.toLowerCase() + "_" + color + ".png");
                }
            }
        }

    }

    // TODO: add mouse listeners
    void onPressHandler() {
    }
    void onMoveHandler() {
    }
    void onReleaseHandler() {
    }

    // TODO: add chess piece images

}

class Square extends JPanel implements MouseListener {

    static final Color SQ_WHITE = new Color(215, 215, 229);
    static final Color SQ_BLACK = new Color(43, 45, 66);
    Color color;
    JLabel imageLabel;
    int x;
    int y;

    public Square(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        // draw the square
        if (color == Color.WHITE) {
            setBackground(SQ_WHITE);
        } else {
            setBackground(SQ_BLACK);
        }
        addMouseListener(this);
        // add image
        imageLabel = new JLabel();
        add(imageLabel);
        //setImage("src\\client\\images\\pawn_white.png");
    }

    public void setImage(String path) {
        imageLabel.setBounds(2, 2, 45, 45);
        imageLabel.setIcon(new ImageIcon(path));
    }

    // on click, change color
    void onClick() {
        if (color == Color.WHITE) {
            setBackground(new Color(215, 233, 229));
        } else {
            setBackground(new Color(43, 65, 66));
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        onClick();
        repaint();
        revalidate();
        System.out.println("Clicked " + x + ", " + y);
        //client.clickSquare(x, y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released " + x + ", " + y);
        // go back to original color
        if (color == Color.WHITE) {
            setBackground(SQ_WHITE);
        } else {
            setBackground(SQ_BLACK);
        }
        repaint();
        revalidate();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

