// Example 3: two ways to visit every value in an array.
// Run it with:  java Ex03LoopingOverArrays.java

public class Ex03LoopingOverArrays {
    public static void main(String[] args) {
        String[] fruits = {"mango", "pawpaw", "pineapple", "banana"};

        // 1. The index loop: use it when you need the POSITION, or want to CHANGE values
        for (int i = 0; i < fruits.length; i++) {
            System.out.println((i + 1) + ". " + fruits[i]);
        }

        // 2. The for-each loop: "for each String fruit in fruits"
        //    Simpler, but it gives you the value only (no index), and you can't
        //    use it to change what's stored in the array.
        for (String fruit : fruits) {
            System.out.println(fruit.toUpperCase());
        }

        // Changing every value needs the index loop:
        int[] prices = {10, 20, 30};
        for (int i = 0; i < prices.length; i++) {
            prices[i] = prices[i] * 2;
        }
        for (int p : prices) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
}
