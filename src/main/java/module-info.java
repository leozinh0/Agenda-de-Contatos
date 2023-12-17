module agenda.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens agenda.Core to javafx.fxml;
    opens agenda.model to javafx.base;
    exports agenda.Core;
}