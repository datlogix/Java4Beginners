import java.util.ArrayList;
import java.util.List;

/** A library member, who can borrow up to BORROW_LIMIT books. */
public class Member {
    public static final int BORROW_LIMIT = 3;

    private final String id;
    private final String name;
    private final List<Book> borrowed = new ArrayList<>();

    public Member(String id, String name) {
        // TODO: validate
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    /** Returns a COPY of the borrowed list, so callers can't change the real one. */
    public List<Book> getBorrowed() {
        return new ArrayList<>(borrowed);
    }

    public boolean canBorrow() {
        return false;   // TODO
    }

    void addLoan(Book book) {
        // TODO
    }

    void removeLoan(Book book) {
        // TODO
    }

    @Override
    public String toString() {
        // TODO: e.g.  "M001 Kofi Mensah (2/3 books)"
        return name;
    }
}
