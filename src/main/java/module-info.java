module org.example.charactermanagementsystem_ij {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens org.example.charactermanagementsystem_ij to javafx.fxml;
    exports org.example.charactermanagementsystem_ij;
}