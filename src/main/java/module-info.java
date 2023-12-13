module agenda.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens agenda.Core to javafx.fxml;
    exports agenda.Core;
}