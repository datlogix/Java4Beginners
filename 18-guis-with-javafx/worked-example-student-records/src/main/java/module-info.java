// The module descriptor: which parts of Java and which libraries this program uses.
module com.makerspace.records {
    requires javafx.controls;        // the window: controls, layouts, charts
    requires java.sql;               // JDBC: Connection, PreparedStatement, ResultSet
    requires org.xerial.sqlitejdbc;  // the SQLite driver itself

    exports com.makerspace.records;  // so JavaFX can start RecordsApp
}
