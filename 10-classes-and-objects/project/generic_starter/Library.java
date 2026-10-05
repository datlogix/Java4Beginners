import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A library: a collection of books and members, and the rules for lending. */
public class Library {
    private final String name;
    private final Map<String, Book> books = new HashMap<>();      // ISBN -> Book
    private final Map<String, Member> members = new HashMap<>();  // member ID -> Member

    public Library(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        // TODO: refuse a duplicate ISBN
    }

    public void addMember(Member member) {
        // TODO: refuse a duplicate member ID
    }

    /** Lends a book. Throws IllegalArgumentException if the ISBN or member ID
     *  doesn't exist, the book is already on loan, or the member is at the limit. */
    public void lend(String isbn, String memberId) {
        // TODO
    }

    public void returnBook(String isbn, String memberId) {
        // TODO
    }

    /** Returns every book whose title or author contains text (ignoring capitals). */
    public List<Book> search(String text) {
        List<Book> found = new ArrayList<>();
        // TODO
        return found;
    }

    /** Returns a multi-line report: counts of books, loans and members. */
    public String report() {
        // TODO
        return name;
    }
}
