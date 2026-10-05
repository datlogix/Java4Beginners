// Module 10 Project, Track A: Library System
// Author: YOUR NAME

/** A book in the library, identified by its ISBN. */
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean onLoan;

    public Book(String isbn, String title, String author) {
        // TODO: reject an empty ISBN, title or author with IllegalArgumentException
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isOnLoan() { return onLoan; }

    // Package-private (no "public"): only classes in this folder, like Library, can call it.
    void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }

    @Override
    public String toString() {
        // TODO: e.g.  "Things Fall Apart by Chinua Achebe [978-0385474542] (on loan)"
        return title;
    }
}
