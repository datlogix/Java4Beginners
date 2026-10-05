// Example 0: the hook. Twelve different shapes, one list, ONE line of
// drawing code:  shape.draw(pen)  ... and each shape draws itself its own way.
// Run it with:  java Ex00ShapeParade.java

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Ex00ShapeParade extends JPanel {

    // Every shape has a position and a colour, and knows how to draw itself.
    abstract static class Shape {
        protected final int x, y;
        protected final Color colour;

        Shape(int x, int y, Color colour) {
            this.x = x;
            this.y = y;
            this.colour = colour;
        }

        abstract void draw(Graphics2D pen);     // every KIND of shape must say how
        abstract String name();
    }

    static class Circle extends Shape {
        Circle(int x, int y, Color c) { super(x, y, c); }
        void draw(Graphics2D pen) {
            pen.setColor(colour);
            pen.fillOval(x - 40, y - 40, 80, 80);
        }
        String name() { return "circle"; }
    }

    static class Square extends Shape {
        Square(int x, int y, Color c) { super(x, y, c); }
        void draw(Graphics2D pen) {
            pen.setColor(colour);
            pen.fillRect(x - 35, y - 35, 70, 70);
        }
        String name() { return "square"; }
    }

    static class Star extends Shape {
        Star(int x, int y, Color c) { super(x, y, c); }
        void draw(Graphics2D pen) {
            Polygon star = new Polygon();
            for (int i = 0; i < 10; i++) {
                double r = (i % 2 == 0) ? 45 : 18;
                double angle = Math.toRadians(-90 + i * 36);
                star.addPoint(x + (int) (r * Math.cos(angle)), y + (int) (r * Math.sin(angle)));
            }
            pen.setColor(colour);
            pen.fillPolygon(star);
        }
        String name() { return "star"; }
    }

    static class Ring extends Circle {          // a Ring IS a Circle, with a hole
        Ring(int x, int y, Color c) { super(x, y, c); }
        @Override
        void draw(Graphics2D pen) {
            super.draw(pen);                    // draw the circle first...
            pen.setColor(Color.WHITE);
            pen.fillOval(x - 20, y - 20, 40, 40);   // ...then cut a hole in it
        }
        @Override
        String name() { return "ring"; }
    }

    private final List<Shape> shapes = new ArrayList<>();

    Ex00ShapeParade() {
        Random random = new Random(3);
        Color[] colours = {Color.RED, Color.ORANGE, new Color(40, 160, 70), Color.BLUE, Color.MAGENTA};
        for (int i = 0; i < 12; i++) {
            int x = 70 + (i % 6) * 120, y = 90 + (i / 6) * 140;
            Color c = colours[random.nextInt(colours.length)];
            switch (random.nextInt(4)) {
                case 0 -> shapes.add(new Circle(x, y, c));
                case 1 -> shapes.add(new Square(x, y, c));
                case 2 -> shapes.add(new Star(x, y, c));
                default -> shapes.add(new Ring(x, y, c));
            }
        }
    }

    void draw(Graphics2D pen) {
        for (Shape shape : shapes) {
            shape.draw(pen);                    // the SAME call... a DIFFERENT drawing
        }
    }

    // ---- Window set-up ----

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D) g;
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        draw(pen);
    }

    public static void main(String[] args) {
        Ex00ShapeParade canvas = new Ex00ShapeParade();
        for (Shape s : canvas.shapes) {
            System.out.print(s.name() + " ");
        }
        System.out.println();
        JFrame window = new JFrame("Shape parade");
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(740, 320));
        window.add(canvas);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
