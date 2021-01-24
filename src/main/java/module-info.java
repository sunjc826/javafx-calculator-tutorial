module org.sunjc {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.sunjc to javafx.fxml;
    exports org.sunjc;
}