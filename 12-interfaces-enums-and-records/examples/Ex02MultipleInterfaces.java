// Example 2: a class can extend only ONE class, but implement MANY interfaces.
// Run it with:  java Ex02MultipleInterfaces.java

public class Ex02MultipleInterfaces {

    interface Drawable {
        String draw();
    }

    interface Movable {
        void moveBy(int dx, int dy);
    }

    interface Named {
        String name();
    }

    static class GameCharacter {
        protected int x, y;
    }

    // Extends one class AND implements three interfaces
    static class Player extends GameCharacter implements Drawable, Movable, Named {
        private final String name;
        Player(String name) { this.name = name; }
        @Override public String draw() { return "@ at (" + x + ", " + y + ")"; }
        @Override public void moveBy(int dx, int dy) { x += dx; y += dy; }
        @Override public String name() { return name; }
    }

    static class Tree implements Drawable {          // trees can be drawn, but don't move
        @Override public String draw() { return "T"; }
    }

    public static void main(String[] args) {
        Player p = new Player("Esi");
        p.moveBy(3, 4);

        Drawable[] scene = {p, new Tree(), new Tree()};
        for (Drawable d : scene) {
            System.out.println(d.draw());
        }

        Movable m = p;           // the same object, seen through a different interface
        m.moveBy(1, 1);
        Named n = p;
        System.out.println(n.name() + ": " + p.draw());
    }
}
