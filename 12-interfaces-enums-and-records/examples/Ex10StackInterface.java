// Example 10: an abstract data type. The Stack interface says WHAT a stack
// does; ArrayStack says HOW, using an array. Code that uses a Stack doesn't
// need to know which implementation it has. (Stack<T> means "a stack of any
// type T": a GENERIC interface. Module 15 explains generics fully.)
// Run it with:  java Ex10StackInterface.java

public class Ex10StackInterface {

    /** A stack: the last item pushed is the first one popped (LIFO). */
    interface Stack<T> {
        void push(T item);
        T pop();          // removes and returns the top item
        T peek();         // returns the top item without removing it
        boolean isEmpty();
        int size();
    }

    static class ArrayStack<T> implements Stack<T> {
        private Object[] items = new Object[4];
        private int count = 0;

        public void push(T item) {
            if (count == items.length) {
                items = java.util.Arrays.copyOf(items, items.length * 2);   // full: double it
            }
            items[count++] = item;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("pop from an empty stack");
            }
            T item = (T) items[--count];
            items[count] = null;
            return item;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("peek at an empty stack");
            }
            return (T) items[count - 1];
        }

        public boolean isEmpty() { return count == 0; }
        public int size() { return count; }
    }

    /** Uses a stack to check that every bracket has a matching partner. */
    static boolean balanced(String text) {
        Stack<Character> open = new ArrayStack<>();
        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                open.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (open.isEmpty()) {
                    return false;
                }
                char last = open.pop();
                if ((c == ')' && last != '(') || (c == ']' && last != '[') || (c == '}' && last != '{')) {
                    return false;
                }
            }
        }
        return open.isEmpty();
    }

    public static void main(String[] args) {
        Stack<String> plates = new ArrayStack<>();
        for (String p : new String[]{"red", "blue", "green", "white", "black"}) {
            plates.push(p);
        }
        System.out.println(plates.size() + " plates; top is " + plates.peek());
        while (!plates.isEmpty()) {
            System.out.print(plates.pop() + " ");
        }
        System.out.println();

        for (String s : new String[]{"(a[b]{c})", "(a[b)c]", "((x)", "f(g(h[1]))"}) {
            System.out.println(s + " balanced? " + balanced(s));
        }
    }
}
