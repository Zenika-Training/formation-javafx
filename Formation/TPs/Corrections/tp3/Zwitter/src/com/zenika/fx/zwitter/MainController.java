package com.zenika.fx.zwitter;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;

import com.zenika.fx.zwitter.client.ZwitterClient;
import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.zweetui.ZweetPane;

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

    // chaque zweet est affiché dans un ZweetPane
    timeline.setCellFactory(new Callback<ListView<Zweet>, ListCell<Zweet>>() {
      @Override
      public ListCell<Zweet> call(final ListView<Zweet> param) {
        final ListCell<Zweet> cell = new ListCell<Zweet>() {

          private ZweetPane pane;

          @Override
          public void updateItem(final Zweet item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
              if (pane == null) {
                pane = new ZweetPane(item);
                pane.prefWidthProperty().bind(timeline.widthProperty().subtract(74d));
                setGraphic(pane);
              } else {
                pane.setData(item);
              }
            } else {
              setGraphic(null);
            }
          }
        };

        return cell;
      }
    });
  }

  public void stop() {
    client.terminate();
  }
}
