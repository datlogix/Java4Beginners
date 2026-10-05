// Example 6: static fields and methods belong to the CLASS, shared by all objects.
// Run it with:  java Ex06StaticMembers.java

public class Ex06StaticMembers {

    static class Ticket {
        private static int nextNumber = 1;     // ONE copy, shared by every Ticket
        static final double PRICE = 15.0;      // a shared constant

        private final int number;              // each Ticket has its own number
        private final String holder;

        Ticket(String holder) {
            this.holder = holder;
            this.number = nextNumber;
            nextNumber++;
        }

        static int ticketsSold() {             // a static method: no particular ticket needed
            return nextNumber - 1;
        }

        @Override
        public String toString() {
            return "Ticket #" + number + " for " + holder;
        }
    }

    public static void main(String[] args) {
        Ticket a = new Ticket("Yaa");
        Ticket b = new Ticket("Kwaku");
        Ticket c = new Ticket("Abena");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        // Static members are used through the CLASS name:
        System.out.println("Sold: " + Ticket.ticketsSold());
        System.out.println("Takings: GHS " + Ticket.ticketsSold() * Ticket.PRICE);
        // You've used static members all along: Math.sqrt, Math.PI, Integer.parseInt...
    }
}
