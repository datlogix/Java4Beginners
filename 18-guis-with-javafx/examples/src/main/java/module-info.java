// The module descriptor: which parts of JavaFX this program uses. You can treat
// it as given. (It's what lets VS Code's Run button start a JavaFX program.)
module com.makerspace.fx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.makerspace.fx to javafx.fxml;    // FXML (Example 13) needs to look inside
    exports com.makerspace.fx;                 // so JavaFX can start our Application classes
}
