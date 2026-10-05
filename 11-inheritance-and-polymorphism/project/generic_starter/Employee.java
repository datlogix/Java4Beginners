// Module 11 Project, Track A: Staff Payroll
// Author: YOUR NAME

/**
 * A member of staff. Every employee is paid from a basic pay (BP):
 *   DA (dearness allowance) = 97% of BP     HRA (house rent allowance) = 10% of BP
 *   PF (provident fund)     = 12% of BP     staff club fund            = 0.1% of BP
 *   gross = BP + DA + HRA                   net = gross - PF - staff club
 */
public abstract class Employee {
    private final String id;
    private final String name;
    private final String address;
    private final String email;
    private final String mobile;
    protected final double basicPay;

    protected Employee(String id, String name, String address, String email, String mobile, double basicPay) {
        // TODO: reject empty id/name, an email without "@", and a basic pay below minimumBasicPay()
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.basicPay = basicPay;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    /** The job title, e.g. "Programmer". */
    public abstract String designation();

    /** The lowest basic pay allowed for this kind of employee. */
    public abstract double minimumBasicPay();

    public double da() { return 0; }          // TODO
    public double hra() { return 0; }         // TODO
    public double pf() { return 0; }          // TODO
    public double staffClub() { return 0; }   // TODO

    public double gross() {
        return 0;   // TODO
    }

    public double net() {
        return 0;   // TODO
    }

    /** Returns a multi-line pay slip showing every figure. */
    public String payslip() {
        // TODO
        return name;
    }

    @Override
    public String toString() {
        // TODO: e.g.  "E001 Abena Mensah, Programmer"
        return name;
    }
}
