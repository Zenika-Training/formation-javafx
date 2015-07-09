package com.zenika.fx.zwitter;

import java.io.InputStream;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ZweetPaneFullJava extends GridPane {

	private final Label userInfo1;
	private final Label userInfo2;
	private final TextArea zweetArea;
	private final ImageView userImg;

	private Zweet zweet;

	public ZweetPaneFullJava() {
		userInfo1 = new Label();
		userInfo1.setId("userInfo1");
		userInfo1.setMaxHeight(200);
		add(userInfo1, 1, 0);
		setHgrow(userInfo1, Priority.ALWAYS);
		setVgrow(userInfo1, Priority.NEVER);

		userInfo2 = new Label();
		userInfo2.setId("userInfo2");
		add(userInfo2, 1, 1);

		zweetArea = new TextArea();
		zweetArea.setId("zweetArea");
		zweetArea.setPrefHeight(75);
		zweetArea.setPrefWidth(75);
		add(zweetArea, 1, 2);
		setHgrow(zweetArea, Priority.ALWAYS);
		setVgrow(zweetArea, Priority.ALWAYS);

		userImg = new ImageView();
		userImg.setId("userImg");
		userImg.setFitWidth(64);
		userImg.setFitHeight(64);
		setRowSpan(userImg, 3);
		setHgrow(userImg, Priority.NEVER);
		setVgrow(userImg, Priority.NEVER);
		setHalignment(userImg, HPos.CENTER);
		setValignment(userImg, VPos.CENTER);
		add(userImg, 0, 0);
	}

	public Zweet getZweet() {
		return zweet;
	}

	public void setZweet(final Zweet zweet) {
		this.zweet = zweet;
		Image userImage;
		try {
			final String img = zweet.getSource().getImg();
			final InputStream inputStream = getClass().getResourceAsStream("avatars/"+img);
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
