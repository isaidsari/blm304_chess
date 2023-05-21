package client;

import chess.Board;
import chess.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    Client client;
    BoardPanel boardPanel;

    public MainFrame(Client client) {
        this.client = client;
        initComponents();
        setSize(420, 440);

        client.hook = this::updateBoard;
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess Client");
        setResizable(false);

        ImageIcon icon = new ImageIcon("src\\client\\images\\icon.jpg");
        setIconImage(icon.getImage());

        boardInit();
        updateBoard();
        pack();
    }


    void boardInit()
    {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        );

        boardPanel = new BoardPanel(client);
        boardPanel.setBounds(0, 0, 400, 400);
        this.add(boardPanel);
    }

    public void updateBoard()
    {
        boardPanel.drawBoard(client.board);
    }
}

class BoardPanel extends JPanel {

    Client client;
    Square[][] squares;

    Square lastClick;

    public BoardPanel(Client client) {
        this.client = client;
        initComponents();
    }

    private void initComponents() {

        setLayout(null);

        squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(new Point(i,j), (i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
                square.setBounds(i * 50, j * 50, 50, 50);
                this.add(square);
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        onPressHandler(e);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        Square s = (Square)e.getSource();
                        s.onRelease();
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

    }

    void drawBoard(Board board) {
        // reset all squares
        for (Square[] row : squares) {
            for (Square square : row) {
                square.reset();
                square.onRelease();
            }
        }

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {

                Piece piece = board.pieceAt(new Point(i, j));
                String color = "";

                if (piece != null) {
                    if (piece.color == Color.WHITE) color = "white";
                    else if (piece.color == Color.BLACK) color = "black";
                    squares[i][j].setImage("src\\client\\images\\" + piece.name.toLowerCase() + "_" + color + ".png");
                }
            }
        }
    }

    void drawPossibleMoves(Point[] moves) {
        for (Point move : moves) {
            squares[move.x][move.y].setImage("src\\client\\images\\possible.png", 10, 10, 25, 25);
        }
    }

    void onPressHandler(MouseEvent e) {
        Square s = (Square)e.getSource();
        s.onClick();

        if (lastClick == null) {
            lastClick = s;
            return;
        }

        if (lastClick == s) {
            lastClick = null;
            s.onRelease();
            return;
        }

        client.move(lastClick.point, s.point);
        lastClick = null;
    }
    void onMoveHandler(MouseEvent e) {
    }
    void onReleaseHandler(MouseEvent e) {
    }

}

class Square extends JPanel {

    static final Color SQ_WHITE = new Color(215, 215, 229);
    static final Color SQ_BLACK = new Color(43, 45, 66);
    Color color;
    JLabel imageLabel;
    Point point;

    public Square(Point point, Color color) {
        this.point = point;
        this.color = color;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        setBackground(color == Color.WHITE ? SQ_WHITE : SQ_BLACK);

        imageLabel = new JLabel();
        add(imageLabel);
    }

    public void setImage(String path) {
        imageLabel.setBounds(2, 2, 45, 45);
        imageLabel.setIcon(new ImageIcon(path));
    }

    public void setImage(String path, int x, int y, int width, int height) {
        imageLabel.setBounds(x, y, width, height);
        imageLabel.setIcon(new ImageIcon(path));
    }

    public void reset() {
        imageLabel.setIcon(null);
    }

    public void onClick() {
        setBackground(color == Color.WHITE ? new Color(215, 233, 229) : new Color(43, 65, 66));
        System.out.println(this.point);
    }

    public void onRelease() {
        setBackground(color == Color.WHITE ? SQ_WHITE : SQ_BLACK);
    }

}
