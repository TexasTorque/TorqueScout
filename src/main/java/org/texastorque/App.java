package org.texastorque;

import org.texastorque.pages.Scoring;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {
    Stage stage;

    private void switchStageScene(Pane page) {
        if (stage.getScene() == null) {
            stage.setScene(new Scene(page, 1200, 1200));
        } else {
            stage.getScene().setRoot(page);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("JavaFX and Maven");
        stage.setScene(null);
        this.stage = stage;
        stage.show();
        System.out.println("Started");
        switchToScoring();

        //Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        /* 
        var p = new Pane();
        p.getChildren().add(new Label("hello world"));
        
        Scene scene = new Scene(p);
        //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void switchToMain() {
        System.out.println("AHHH!");
    }

    private void switchToScoring() {
        Scoring window = new Scoring();
        window.getSubmit().setOnAction(e -> {
            switchToMain();
        });
        System.out.println("Scoring!");
        switchStageScene(window.getPanel());
    }

}
