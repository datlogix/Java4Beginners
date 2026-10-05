package com.makerspace.shop;

public class ShopApp {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.add("Arduino Uno", 180, 3);
        cart.add("Breadboard", 25, 4);
        System.out.printf("%d items, subtotal %.2f, total to pay GHS %.2f%n",
                cart.itemCount(), cart.subtotal(), cart.total());
    }
}
