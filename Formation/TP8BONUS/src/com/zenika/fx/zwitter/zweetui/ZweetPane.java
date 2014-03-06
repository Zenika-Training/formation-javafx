package com.zenika.fx.zwitter.zweetui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import com.zenika.fx.zwitter.client.ZwitterClient;
import com.zenika.fx.zwitter.model.Zweet;

public class ZweetPane extends GridPane {

  @FXML
  private ImageView userImg;
  @FXML
  private Label userInfo1;
  @FXML
  private Label userInfo2;
  @FXML
  private TextArea zweetArea;

  private Zweet data;

  public ZweetPane(Zweet zweet) {
    this.data = zweet;
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zweetPane.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void initialize() {
    setData(this.data);
  }

  public void setData(final Zweet data) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        ZweetPane.this.data = data;
        Image userImage;
        try {
          userImage = new Image(ZwitterClient.getImageAsStream(data.getSource().getImg()));
        } catch (Exception e) {
          userImage = new Image(getClass().getResourceAsStream("anonymous.png"));
        }
        userImg.setImage(userImage);
        userInfo1.setText(data.getSource().getDisplayName());
        userInfo2.setText(data.getSource().getUsername());
        zweetArea.setText(data.getText());
      }
    });
  }
}
