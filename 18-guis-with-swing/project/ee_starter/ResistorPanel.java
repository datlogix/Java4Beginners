import java.awt.*;
import javax.swing.*;

/** Draws a resistor with four coloured bands. */
public class ResistorPanel extends JPanel {
    private BandColour[] bands = {BandColour.YELLOW, BandColour.VIOLET, BandColour.RED, BandColour.GOLD};

    public ResistorPanel() {
        setPreferredSize(new Dimension(520, 160));
        setBackground(new Color(235, 240, 245));
    }

    public void setBands(BandColour[] bands) {
        this.bands = bands.clone();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int mid = getHeight() / 2;
        // the wire
        pen.setColor(Color.GRAY);
        pen.setStroke(new BasicStroke(6));
        pen.drawLine(20, mid, getWidth() - 20, mid);
        // the body
        pen.setColor(new Color(225, 195, 150));
        pen.fillRoundRect(110, mid - 40, 300, 80, 50, 50);
        // TODO: draw the four bands in their colours: three close together on the
        //       left, and the tolerance band set apart on the right
    }
}
