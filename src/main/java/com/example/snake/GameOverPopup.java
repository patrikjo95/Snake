package com.example.snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameOverPopup {
    private Stage stage;
    private Scene scene;
    private BorderPane root;

    private VBox vbox;
    private HBox hbox;
    private Label label;
    private Button restartButton;
    private Button exitButton;


    public GameOverPopup(String text){
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        root = new BorderPane();
        scene = new Scene(root, 300, 150);
        scene.setFill(Color.TRANSPARENT);

        vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);

        label = new Label(text);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(15));
        label.setWrapText(true);

        restartButton = new Button("Restart");
        exitButton = new Button("Exit");

        hbox.getChildren().add(restartButton);
        hbox.getChildren().add(exitButton);
        vbox.getChildren().add(label);
        vbox.getChildren().add(hbox);
        root.setCenter(vbox);
        stage.setScene(scene);
    }

    public void setRestartListener(EventHandler<ActionEvent> listener){
        restartButton.setOnAction(listener);
    }

    public void setExitListener(EventHandler<ActionEvent> listener){
        exitButton.setOnAction(listener);
    }

    public void show(){
        stage.show();
    }

    public void close(){
        stage.close();
    }



}
