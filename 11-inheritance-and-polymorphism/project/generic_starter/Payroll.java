import java.util.ArrayList;
import java.util.List;

/** All the staff in a department, and payroll calculations over them. */
public class Payroll {
    private final String department;
    private final List<Employee> staff = new ArrayList<>();

    public Payroll(String department) {
        this.department = department;
    }

    public void hire(Employee e) {
        // TODO: refuse a duplicate ID
    }

    public Employee find(String id) {
        // TODO: return the employee, or throw IllegalArgumentException
        return null;
    }

    public double totalGross() {
        return 0;   // TODO
    }

    public double totalNet() {
        return 0;   // TODO
    }

    public Employee highestPaid() {
        return null;   // TODO
    }

    /** Returns a table: one line per employee (ID, name, designation, gross, net), then totals. */
    public String summary() {
        return department;   // TODO
    }
}
