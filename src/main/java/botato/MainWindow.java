package botato;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Botato botato;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image botatoImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Botato.png")));

    /**
     * Initializes the scroll pane and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Display welcome message
        dialogContainer.getChildren().addAll(
                DialogBox.getBotatoDialog(Ui.showWelcome(), botatoImage)
        );
    }

    /** Injects the Botato instance */
    public void setBotato(Botato b) {
        botato = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Botato's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = botato.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotatoDialog(response, botatoImage)
        );
        userInput.clear();
        // (Temporary?) measure to quit application when 'bye' is typed (i.e. user wants to exit)
        if (Objects.equals(response, "Hope I helped! See you~")) {
            Platform.exit();
        }
    }
}
