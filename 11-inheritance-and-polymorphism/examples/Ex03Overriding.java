// Example 3: overriding. A subclass can REPLACE an inherited method with its
// own version, and still call the original with super.method().
// Run it with:  java Ex03Overriding.java

public class Ex03Overriding {

    static class Employee {
        protected final String name;
        protected final double basicPay;

        Employee(String name, double basicPay) {
            this.name = name;
            this.basicPay = basicPay;
        }

        double monthlyPay() {
            return basicPay;
        }

        String payslip() {
            return String.format("%-10s GHS %8.2f", name, monthlyPay());
        }
    }

    static class Manager extends Employee {
        private final double allowance;

        Manager(String name, double basicPay, double allowance) {
            super(name, basicPay);
            this.allowance = allowance;
        }

        @Override                          // the compiler checks this really overrides something
        double monthlyPay() {
            return super.monthlyPay() + allowance;   // the original, plus a bit
        }

        @Override
        String payslip() {
            return super.payslip() + "  (incl. allowance " + allowance + ")";
        }
    }

    public static void main(String[] args) {
        Employee abena = new Employee("Abena", 3000);
        Manager kofi = new Manager("Kofi", 4500, 1200);
        System.out.println(abena.payslip());
        System.out.println(kofi.payslip());
        // Notice: payslip() in Employee calls monthlyPay(). For kofi, that call
        // runs MANAGER's monthlyPay, even inside Employee's code.
    }
}
