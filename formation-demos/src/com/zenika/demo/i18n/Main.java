package com.zenika.demo.i18n;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	static StackPane root = null;
	
	@Override
	public void start(Stage primaryStage) {

		
		root = new StackPane();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		startLanguage(Language.FR);

	}

	public static void main(String[] args) {
		launch(args);
	}

	static void startLanguage(Language lang) {
		try {

			ResourceBundle resource =  ResourceBundle.getBundle("com/zenika/demo/i18n/resources", lang.getLocale());

			BorderPane pane = (BorderPane)FXMLLoader.load(
					Main.class.getResource("Sample.fxml"),
					resource);

			root.getChildren().clear();
			root.getChildren().add(pane);
			root.setNodeOrientation(lang.getNodeOrientation());
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
