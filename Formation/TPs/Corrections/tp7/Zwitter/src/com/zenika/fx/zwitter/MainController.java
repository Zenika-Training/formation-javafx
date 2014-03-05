package com.zenika.fx.zwitter;

import java.util.LinkedList;
import java.util.Set;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import javafx.util.Duration;

import com.zenika.fx.zwitter.client.ZwitterClient;
import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.model.ZwitterUser;
import com.zenika.fx.zwitter.search.SearchTask;
import com.zenika.fx.zwitter.zweetui.SearchBox;
import com.zenika.fx.zwitter.zweetui.ZweetPane;

public class MainController {
  
  private static final ZwitterUser ME = new ZwitterUser("@" + System.getProperty("user.name"),
      "znk.png", "Zenika Stagiaire");

  @FXML
  private SearchBox searchBox;
  @FXML
  private ListView<Zweet> timeline;
  @FXML
  private TextArea zweetArea;
  @FXML
  private ProgressBar zweetCompletion;
  @FXML
  private Label zweetCompletionText;

  private ZwitterClient client;

  @FXML
  public void initialize() {
    // ceci est un exemple d'utilisation du ZwitterSDK
    final ObservableList<Zweet> timelineModel =
        FXCollections.observableList(new LinkedList<Zweet>());
    client = new ZwitterClient();
    client.startTimeline(timelineModel);

    timeline.setItems(timelineModel);

    // implement search
    searchBox.textProperty().addListener(new ChangeListener<String>() {
      private SearchTask searchTask;

      @Override
      public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
        if (newValue != null && newValue.length() > 0) {
          if (searchTask != null && searchTask.isRunning())
            searchTask.cancel();

          searchTask = new SearchTask(timelineModel, newValue);
          searchTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent wse) {
              @SuppressWarnings("unchecked")
              Set<Zweet> result = (Set<Zweet>) wse.getSource().getValue();
              ObservableList<Zweet> foundZweets = FXCollections.observableArrayList(result);
              timeline.setItems(foundZweets);
            }
          });
          new Thread(searchTask).start();
        } else {
          if (searchTask != null && searchTask.isRunning())
            searchTask.cancel();
          timeline.setItems(timelineModel);
        }
      }
    });

    // chaque zweet est affiché dans un ZweetPane
    // la première apparition d'un zweet est animée
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
                Platform.runLater(new Runnable() {
                  public void run() {
                    setGraphic(pane);
                  }
                });
              } else {
                pane.setData(item);
              }
              
              if (!searchBox.isSearchActive()) {
                Platform.runLater(new Runnable() {
                  public void run() {
                    if (item == timelineModel.get(0)) {
                      Transition transition = createTransition(pane, item.getSource());
                      transition.play();
                    }
                  }
                });
              }
            } else {
              Platform.runLater(new Runnable() {
                public void run() {
                  setGraphic(null);
                }
              });
            }
          }
        };

        return cell;
      }
    });
    
    IntegerBinding zweetLengthProperty = new IntegerBinding() {
      {
        super.bind(zweetArea.textProperty());
      }
      
      @Override
      protected int computeValue() {
        if (zweetArea.getText() == null || zweetArea.getText().length() == 0)
          return 0;
        return zweetArea.getText().length();
      }
    };
    
    zweetCompletionText.textProperty().bind(zweetLengthProperty.asString());
    zweetCompletion.progressProperty().bind(zweetLengthProperty.divide(140d));
    
    zweetCompletion.getStyleClass().add("completion");
    zweetCompletionText.getStyleClass().add("completion");
    zweetLengthProperty.addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> paramObservableValue, Number oldValue,
          Number newValue) {
        if (newValue.intValue() > 140) {
          zweetCompletion.getStyleClass().addAll("overflow");
          zweetCompletionText.getStyleClass().addAll("overflow");
        } else {
          zweetCompletion.getStyleClass().removeAll("overflow");
          zweetCompletionText.getStyleClass().removeAll("overflow");
        }
      }
    });
  }

  public void sendZweet() {
    client.publish(new Zweet(ME, zweetArea.getText()));
    zweetArea.clear();
    zweetArea.requestFocus();
  }

  public void stop() {
    client.terminate();
  }
  
  private static Transition createTransition(Node pane, ZwitterUser source) {
    FadeTransition fade = new FadeTransition(Duration.seconds(1d), pane);
    fade.setFromValue(0d);
    fade.setToValue(1d);

    ScaleTransition scale1 = new ScaleTransition(Duration.millis(500d), pane);
    scale1.setFromX(0d);
    scale1.setFromY(0d);

    if (ME.equals(source)) {
      scale1.setToX(1d);
      scale1.setToY(1d);
      return new ParallelTransition(fade, scale1);
    }
    scale1.setToX(0.5);
    scale1.setToY(0.5);

    ScaleTransition scale2 = new ScaleTransition(Duration.millis(500d), pane);
    scale2.setFromX(0.5);
    scale2.setFromY(0.5);
    scale2.setToX(1d);
    scale2.setToY(1d);
    scale2.setCycleCount(3);
    scale2.setAutoReverse(true);

    return new ParallelTransition(fade, new SequentialTransition(scale1, scale2));
  }
}
