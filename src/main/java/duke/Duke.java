package duke;

import duke.command.*;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Initializes other classes and get responses for input.
 */
public class Duke extends Application {

    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Stage stage;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a duke object with a given filepath to save the output.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList  = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        parser = new Parser(taskList, ui);
        ui.taskList = taskList;
    }

    /**
     * Starts the program.
     */
    public void run() {
        assert taskList.getSize() == 0 : "Initial TaskList should not be empty";
        ui.start(parser, storage, taskList);
    }

    /**
     * Main method for the whole program that creates a new Duke object and calls run().
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Setting up the layout (no longer used since fxml is used.
     * @param stage the main stage.
     */
    @Override
    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
////        dialogContainer.getChildren().addAll(
////                DialogBox.getUserDialog(userText, new ImageView(user)),
////                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
////        );
//
//        String input = userInput.getText().trim();
//        String response = getResponse(input);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(input, user),
//                DialogBox.getDukeDialog(response,duke)
//        );
//        if (input.equals("bye")) {
//            Platform.exit();
//            System.exit(0);
//        }
//        userInput.clear();
//
//    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.updateFile(taskList.getList());
            return "Bye. Hope to see you again soon!";
        } else {
            try {
                return  parser.parseLine(input);
            } catch (DukeException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "Must input an integer";
            }
        }
    }
}
