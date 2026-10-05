// Example 10: putting it together. An abstract Employee and three kinds of
// staff, each paid differently, processed by one polymorphic loop.
// Run it with:  java Ex10PayrollReport.java

import java.util.List;

public class Ex10PayrollReport {

    abstract static class Employee {
        private final String id;
        private final String name;

        Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        abstract double grossPay();          // every kind of employee is paid differently
        abstract String kind();

        double tax() {                        // a simple flat rate, shared by everyone
            return grossPay() * 0.15;
        }

        double netPay() {
            return grossPay() - tax();
        }

        @Override
        public String toString() {
            return String.format("%-5s %-12s %-10s %9.2f %8.2f %9.2f", id, name, kind(), grossPay(), tax(), netPay());
        }
    }

    static class Salaried extends Employee {
        private final double monthlySalary;
        Salaried(String id, String name, double monthlySalary) {
            super(id, name);
            this.monthlySalary = monthlySalary;
        }
        @Override double grossPay() { return monthlySalary; }
        @Override String kind() { return "salaried"; }
    }

    static class Hourly extends Employee {
        private final double rate;
        private final double hours;
        Hourly(String id, String name, double rate, double hours) {
            super(id, name);
            this.rate = rate;
            this.hours = hours;
        }
        @Override double grossPay() {
            double overtime = Math.max(0, hours - 160);
            return rate * Math.min(hours, 160) + rate * 1.5 * overtime;
        }
        @Override String kind() { return "hourly"; }
    }

    static class Commissioned extends Salaried {
        private final double sales;
        Commissioned(String id, String name, double base, double sales) {
            super(id, name, base);
            this.sales = sales;
        }
        @Override double grossPay() { return super.grossPay() + sales * 0.05; }
        @Override String kind() { return "sales"; }
    }

    public static void main(String[] args) {
        List<Employee> staff = List.of(
                new Salaried("E01", "Abena Mensah", 4200),
                new Hourly("E02", "Kojo Badu", 25, 172),
                new Commissioned("E03", "Esi Quaye", 2500, 64000),
                new Hourly("E04", "Yaw Boateng", 22, 140));

        System.out.printf("%-5s %-12s %-10s %9s %8s %9s%n", "ID", "Name", "Type", "Gross", "Tax", "Net");
        double payroll = 0;
        for (Employee e : staff) {
            System.out.println(e);
            payroll += e.grossPay();
        }
        System.out.printf("Total payroll: GHS %.2f%n", payroll);
    }
}
