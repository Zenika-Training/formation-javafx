package com.zenika.fx.zwitter;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import com.zenika.fx.zwitter.client.ZwitterClient;
import com.zenika.fx.zwitter.model.Zweet;

public class MainController {
  
  @FXML
  private ListView<Zweet> timeline;

  private ZwitterClient client;

  @FXML
  public void initialize() {
    // ceci est un exemple d'utilisation du ZwitterSDK
    ObservableList<Zweet> timelineModel = FXCollections.observableList(new LinkedList<Zweet>());
    client = new ZwitterClient();
    client.startTimeline(timelineModel);

    timeline.setItems(timelineModel);
  }

  public void stop() {
    client.terminate();
  }
}
