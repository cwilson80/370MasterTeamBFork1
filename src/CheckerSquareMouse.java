import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckerSquareMouse extends MouseAdapter {

    private final CheckerSquare square;

    CheckerSquareMouse(CheckerSquare square) {
        super();
        this.square = square;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            square.toggleChecker(Color.YELLOW);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            square.toggleChecker(Color.BLUE);
        }
    }
}
