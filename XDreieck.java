import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
class MyCanvas extends JComponent {

    private Dreieck dreieck;

    public MyCanvas(Dreieck dreieck) {
        this.setPreferredSize(new Dimension(600, 500));
        this.dreieck = dreieck;
    }

    @Override
    public void paint(Graphics g) {
        Kreis kreis = dreieck.inkreis();
        g.drawLine((int)(dreieck.getEcke1().getX() + 300), (int)(dreieck.getEcke1().getY() + 250), 
(int)(dreieck.getEcke2().getX() + 300), (int)(dreieck.getEcke2().getY() + 250));
        g.drawLine((int)(dreieck.getEcke2().getX() + 300), (int)(dreieck.getEcke2().getY() + 250), 
(int)(dreieck.getEcke3().getX() + 300), (int)(dreieck.getEcke3().getY() + 250));
        g.drawLine((int)(dreieck.getEcke3().getX() + 300), (int)(dreieck.getEcke3().getY() + 250), 
(int)(dreieck.getEcke1().getX() + 300), (int)(dreieck.getEcke1().getY() + 250));
        g.drawOval((int)(kreis.getX() - kreis.getRadius() + 300),
                (int)(kreis.getY() - kreis.getRadius() + 250),
                (int)(kreis.getRadius()*2),
                (int)(kreis.getRadius()*2));
    }
}

@SuppressWarnings("serial")
class MyButton extends JButton {

    MyButton(String label) {
        super(label);
        setPreferredSize(new Dimension(120, 30));
    }
}

abstract class ClickListener implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

abstract class KeyPressedListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

public class XDreieck {

    private XDreieck(){}

    public static void main(String[] a) {
        final Dreieck dreieck = Dreieck.newDreieck(125, 75, -50, -50);
        final JFrame window = new JFrame();
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(600, 600, 600, 600);

        final MyCanvas canvas = new MyCanvas(dreieck);
        window.getContentPane().add(canvas);

        JButton widen = addButton(window, dreieck, "Widen!", DreieckAction.WIDEN);
        JButton narrow = addButton(window, dreieck, "Narrow!", DreieckAction.NARROW);

        KeyListener kl = new KeyPressedListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        dreieck.performAction(DreieckAction.ENLARGE);
                        break;
                    case KeyEvent.VK_DOWN:
                        dreieck.performAction(DreieckAction.SHRINK);
                        break;
                    case KeyEvent.VK_LEFT:
                        dreieck.performAction(DreieckAction.ROTATE_LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        dreieck.performAction(DreieckAction.ROTATE_RIGHT);
                        break;
                    default:
                        return;
                }
                window.repaint();
            }
        };
        for (JComponent c : new JComponent[] { canvas, widen, narrow }) {
            c.addKeyListener(kl);
        }

        window.setVisible(true);
    }

    private static JButton addButton(final JFrame window, final Dreieck dreieck, final String label, final DreieckAction action) {
        JButton button = new MyButton(label);
        button.addMouseListener(new ClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dreieck.performAction(action);
                window.repaint();
            }
        });
        window.getContentPane().add(button);
        return button;
    }

}

