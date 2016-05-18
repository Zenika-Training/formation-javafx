package com.zenika.demo.dialogs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


public class SampleController implements Initializable {

	@FXML
	private Button bouton;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		bouton.setOnAction(this::onAction);


	}

	private void onAction(ActionEvent actionEvent) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Une boite d'erreur");
		alert.setTitle("Erreur!");
		alert.setContentText("Une erreur est survenue");
		alert.showAndWait();

	}


}
