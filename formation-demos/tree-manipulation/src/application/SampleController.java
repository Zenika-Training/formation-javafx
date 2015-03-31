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

    
	@FXML
	void showPopup(ActionEvent event) {
        Text text = new Text(10, 40, "Hello World!");
        text.setFont(new Font(40));
        
        VBox box = new VBox();
        box.getChildren().add(text);
        
        Scene scene = new Scene(box);
        
        BorderPane parent = ((BorderPane)image.getParent());
        
        parent.getChildren().remove(image);
        
        box.getChildren().add(image);
        
		
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		
		stage.setOnCloseRequest( e ->
		{
				//re-add
				box.getChildren().remove(image);
				parent.setCenter(image);
		} ); 

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		image.setImage(new Image("/application/znk.png"));
		
	}



}
