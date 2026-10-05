/** A full professor, who also receives a research allowance of 5% of BP. */
public class Professor extends Employee {

    public Professor(String id, String name, String address, String email, String mobile, double basicPay) {
        super(id, name, address, email, mobile, basicPay);
    }

    @Override
    public String designation() {
        return "Professor";
    }

    @Override
    public double minimumBasicPay() {
        return 9000;
    }

    public double researchAllowance() {
        return 0;   // TODO
    }

    // TODO: override gross() to add the research allowance (use super.gross())
    // TODO: override payslip() to show the research allowance line too
}
