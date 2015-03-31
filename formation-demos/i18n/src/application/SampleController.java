package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class SampleController implements Initializable {

	 @FXML
	    private TextArea text;
	
	@FXML
	void setFr(ActionEvent event) {
		Main.startLanguage(Language.FR);
	}

	@FXML
	void setEn(ActionEvent event) {
		Main.startLanguage(Language.EN);
	}

	@FXML
	void setAr(ActionEvent event) {
		Main.startLanguage(Language.AR);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		text.setText(resources.getString("content"));
		
		
		
	}
}
