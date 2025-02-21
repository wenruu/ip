package botato;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import javafx.scene.web.WebView;
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
        if (Objects.equals(response, "Opening my guide in a new window...")) {  // Fixed typo here
            openMarkdownViewer("docs/README.md");
        }
    }

    private void openMarkdownViewer(String filePath) {
        Path filepath = Path.of(filePath);

        if (!Files.exists(filepath)) {
            System.err.println("Markdown file not found: " + filePath);
            return;
        }

        String markdownContent = readMarkdownFile(filepath);
        String htmlContent = convertMarkdownToHtml(markdownContent);

        Platform.runLater(() -> {
            WebView webView = new WebView();
            webView.getEngine().loadContent(htmlContent);

            Scene scene = new Scene(webView, 800, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Botato Guide");
            stage.show();
        });
    }
    private String readMarkdownFile(Path filePath) {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading file.";
        }
    }

    private String convertMarkdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
}
