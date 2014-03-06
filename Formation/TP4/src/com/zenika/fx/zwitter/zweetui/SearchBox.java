package com.zenika.fx.zwitter.zweetui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SearchBox extends Region {

  private TextField searchText;
  private Button clearButton;

  public SearchBox() {
    super();
    setId("SearchBox");
    setMinHeight(24);
    setPrefSize(150, 24);
    setMaxHeight(24);

    searchText = new TextField();
    searchText.setPromptText("Rechercher");
    searchText.prefWidthProperty().bind(this.widthProperty().subtract(20d));
    searchText.getStyleClass().add("search");

    clearButton = new Button();
    clearButton.setVisible(false);
    clearButton.getStyleClass().add("clearSearch");

    getChildren().addAll(searchText, clearButton);

    clearButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent paramT) {
        searchText.clear();
        searchText.requestFocus();
      }
    });

    searchText.textProperty().addListener(new ChangeListener<String>() {

      @Override
      public void changed(ObservableValue<? extends String> paramObservableValue, String oldValue,
          String newValue) {
        clearButton.setVisible(newValue != null && newValue.length() > 0);
      }
    });
  }

  @Override
  protected void layoutChildren() {
    searchText.resize(getWidth(), getHeight());
    clearButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
  }

}
