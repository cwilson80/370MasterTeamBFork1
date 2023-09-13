import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckerboardGUI extends JFrame {
    private static final int BOARD_SIZE = 8;
    private CheckerSquare[][] squares;

    public CheckerboardGUI() {
        setTitle("Checkerboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        squares = new CheckerSquare[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                CheckerSquare square = new CheckerSquare(row, col);
                squares[row][col] = square;
                boardPanel.add(square);

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            square.toggleChecker(Color.YELLOW);
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            square.toggleChecker(Color.BLUE);
                        }
                    }
                });
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        JButton clearBoardButton = new JButton("Clear Board");
        clearBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoard();
            }
        });

        JButton helpButton = new JButton("?");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelpDialog();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startGameButton);
        buttonPanel.add(clearBoardButton);
        buttonPanel.add(helpButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class CheckerSquare extends JPanel {
        private Color checkerColor = null;
        private int row, col;

        CheckerSquare(int row, int col) {
            this.row = row;
            this.col = col;
            setPreferredSize(new Dimension(50, 50));
        }

        public void toggleChecker(Color color) {
            if (checkerColor == null) {
                checkerColor = color;
            } else {
                checkerColor = null;
            }
            repaint(); // Repaint the square to show or hide the checker
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the square
            g.setColor((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            // Draw the checker if it exists
            if (checkerColor != null) {
                g.setColor(checkerColor);
                g.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
            }
        }
    }

    private void startGame() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (row < 3 && (row + col) % 2 == 1) {
                    squares[row][col].toggleChecker(Color.YELLOW);
                } else if (row > 4 && (row + col) % 2 == 1) {
                    squares[row][col].toggleChecker(Color.BLUE);
                }
            }
        }
    }

    private void clearBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col].toggleChecker(null);
            }
        }
    }

    private void showHelpDialog() {
        String message = "Left-click a square to add a yellow checker.\n"
                + "Right-click a square to add a blue checker.\n"
                + "Click again to remove a checker.\n"
                + "Use the 'Start Game' button to set up the board for a game.";
        JOptionPane.showMessageDialog(this, message, "How to Add/Remove Checkers", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CheckerboardGUI();
        });
    }
}
