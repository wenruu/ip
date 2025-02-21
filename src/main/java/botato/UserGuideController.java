package botato;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UserGuideController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contentBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind the width of each label to the width of the ScrollPane
        for (var node : contentBox.getChildren()) {
            if (node instanceof Label label) {
                label.prefWidthProperty().bind(scrollPane.widthProperty().subtract(60));
            }
        }
    }
}