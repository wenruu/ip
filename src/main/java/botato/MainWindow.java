package botato;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

import javafx.stage.Stage;

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
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image botatoImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/botato.png")));

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
        try (InputStream inputStream = MainWindow.class.getClassLoader().getResourceAsStream("docs/README.md")) {
            if (inputStream == null) {
                System.err.println("File not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        // Quit application when 'bye' is typed (i.e. user wants to exit)
        if (Objects.equals(response, "Hope I helped! See you~")) {
            Platform.exit();
        }
        // Opens the user guide after a short delay
        if (Objects.equals(response, "Opening my guide in a new window...")) {
            try {
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserGuide.fxml"));
                ScrollPane root = loader.load();

                // Create the scene
                Scene scene = new Scene(root, 600, 600);

                // Apply the CSS file
                scene.getStylesheets().add(getClass().getResource("/css/user-guide.css").toExternalForm());

                // Create a new stage for the User Guide
                Stage userGuideStage = new Stage();
                userGuideStage.setTitle("Botato User Guide");
                userGuideStage.setX(0);
                userGuideStage.setY(0);
                userGuideStage.setScene(scene);
                userGuideStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
