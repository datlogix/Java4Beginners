// Example 9: two-dimensional arrays: an array of rows, each row an array.
// Run it with:  java Ex09Grids2D.java

public class Ex09Grids2D {
    public static void main(String[] args) {
        // A seating plan: 3 rows of 5 seats. 'X' is taken, '.' is free.
        char[][] seats = {
            {'X', '.', '.', 'X', 'X'},
            {'.', '.', '.', '.', '.'},
            {'X', 'X', '.', 'X', '.'},
        };

        System.out.println("Rows: " + seats.length + ", seats per row: " + seats[0].length);
        System.out.println("Row 0, seat 3: " + seats[0][3]);    // [row][column]

        seats[1][2] = 'X';      // book the middle seat of row 1

        // Print it with row letters and seat numbers
        System.out.println("   1 2 3 4 5");
        for (int row = 0; row < seats.length; row++) {
            System.out.print((char) ('A' + row) + "  ");
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col] + " ");
            }
            System.out.println();
        }

        // Count the free seats
        int free = 0;
        for (char[] row : seats) {
            for (char seat : row) {
                if (seat == '.') {
                    free++;
                }
            }
        }
        System.out.println("Free seats: " + free);

        // A grid of numbers, created empty and filled by a loop
        int[][] table = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                table[r][c] = (r + 1) * (c + 1);
            }
        }
        System.out.println("table[2][3] = " + table[2][3]);    // 3 x 4 = 12
    }
}
