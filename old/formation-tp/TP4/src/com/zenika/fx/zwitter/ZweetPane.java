package com.zenika.fx.zwitter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ZweetPane extends GridPane implements Initializable {

	@FXML
	private ImageView userImg;

	@FXML
	private Label userInfo1;

	@FXML
	private Label userInfo2;

	@FXML
	private TextArea zweetArea;

	private Zweet zweet;

	public ZweetPane(final Zweet zweet) {
		this.zweet = zweet;

		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zweetPane.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		setZweet(zweet);
	}

	public Zweet getZweet() {
		return zweet;
	}

	public void setZweet(final Zweet zweet) {
		this.zweet = zweet;
		Image userImage;
		try {
			final String img = zweet.getSource().getImg();
			final InputStream inputStream = getClass().getResourceAsStream("avatars/" + img);
			userImage = new Image(inputStream);
		} catch (Exception e) {
			final InputStream inputStream = getClass().getResourceAsStream("avatars/anonymous.png");
			userImage = new Image(inputStream);
		}
		userImg.setImage(userImage);
		userInfo1.setText(zweet.getSource().getDisplayName());
		userInfo2.setText(zweet.getSource().getUsername());
		zweetArea.setText(zweet.getText());
	}

}
