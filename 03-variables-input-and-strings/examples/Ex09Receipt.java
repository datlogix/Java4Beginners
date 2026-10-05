// Example 9: putting it together. A shop receipt with input and printf.
// Run it with:  java Ex09Receipt.java

import java.util.Scanner;

public class Ex09Receipt {
    public static void main(String[] args) {
        final double VAT_RATE = 0.15;
        Scanner in = new Scanner(System.in);

        System.out.print("Item name: ");
        String item = in.nextLine().trim();
        System.out.print("Price each (GHS): ");
        double price = Double.parseDouble(in.nextLine().trim());
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(in.nextLine().trim());

        double subtotal = price * quantity;
        double vat = subtotal * VAT_RATE;
        double total = subtotal + vat;

        System.out.println();
        System.out.println("=".repeat(32));
        System.out.printf("%-20s %11s%n", "MAKERSPACE SHOP", "RECEIPT");
        System.out.println("=".repeat(32));
        System.out.printf("%-20s x%-3d%8.2f%n", item, quantity, subtotal);
        System.out.printf("%-24s%8.2f%n", "VAT (15%)", vat);
        System.out.println("-".repeat(32));
        System.out.printf("%-24s%8.2f%n", "TOTAL (GHS)", total);
        System.out.println("=".repeat(32));
    }
}
