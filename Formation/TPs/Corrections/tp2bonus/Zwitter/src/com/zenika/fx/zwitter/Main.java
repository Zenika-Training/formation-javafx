package com.zenika.fx.zwitter;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
  private MainController controller;

  @Override
  public void start(Stage primaryStage) {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("MainScreen.fxml"));
    try {
      BorderPane root = (BorderPane) fxmlLoader.load();
      this.controller = fxmlLoader.getController();
      
      Scene scene = new Scene(root, 300, 500);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Zwitter");
      primaryStage.show();
      
      primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
          getController().stop();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public MainController getController() {
    return this.controller;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
