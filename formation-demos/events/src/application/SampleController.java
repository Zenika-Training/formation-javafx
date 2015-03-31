package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SampleController implements Initializable {

	@FXML
	private Button bouton;
    

    @FXML
    private ImageView image;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Trace.register(bouton);
		
		bouton.addEventHandler(ActionEvent.ACTION,				
				e -> {  bouton.fireEvent(new MyEvent(MyEvent.BOUM)); } 	);
		
		image.setImage(new Image("/application/znk.png"));

	}



}
