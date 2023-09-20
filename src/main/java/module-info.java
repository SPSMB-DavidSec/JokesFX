module cz.spsmb.secda1.jokes.jokes_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.spsmb.secda1.jokes.jokes_fx to javafx.fxml;
    exports cz.spsmb.secda1.jokes.jokes_fx;
}