module agenda.agenda {
    requires javafx.controls;
    requires javafx.fxml;


    opens agenda.Core to javafx.fxml;
    exports agenda.Core;
}