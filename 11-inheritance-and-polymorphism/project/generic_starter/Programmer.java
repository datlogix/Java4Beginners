public class Programmer extends Employee {

    public Programmer(String id, String name, String address, String email, String mobile, double basicPay) {
        super(id, name, address, email, mobile, basicPay);
    }

    @Override
    public String designation() {
        return "Programmer";
    }

    @Override
    public double minimumBasicPay() {
        return 3000;
    }
}
