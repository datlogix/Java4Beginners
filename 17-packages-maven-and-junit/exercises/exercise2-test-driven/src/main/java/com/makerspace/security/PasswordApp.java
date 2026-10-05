package com.makerspace.security;

import java.util.Scanner;

public class PasswordApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Choose a password: ");
        String password = in.nextLine();
        System.out.println("Strength: " + PasswordChecker.strength(password));
        for (String problem : PasswordChecker.problems(password)) {
            System.out.println("  - " + problem);
        }
    }
}
