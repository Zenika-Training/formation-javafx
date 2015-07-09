package com.zenika.fx.zwitter;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class MainController implements Initializable {
	@FXML
	void showPopup(ActionEvent event) {

		Node node = (Node) event.getSource();

		if (popup.isShowing()) {
			popup.hide();
		} else {
			popup.show(node, 0d,0d);
			popup.centerOnScreen();
		}
	}

	Popup popup;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		popup = new Popup();
		popup.setWidth(400);
		popup.setHeight(300);
		
		Text text = new Text();
		text.setText("Hello, World");
		
		popup.getContent().add(text);
	
		

	}

}
