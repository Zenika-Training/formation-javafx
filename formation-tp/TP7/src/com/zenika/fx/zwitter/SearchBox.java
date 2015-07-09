package com.zenika.fx.zwitter;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SearchBox extends Region {

	private final TextField searchText;
	private final Button clearButton;

	public SearchBox() {
		setId("SearchBox");
		setMinHeight(24);
		setPrefSize(150, 24);
		setMaxHeight(24);

		searchText = new TextField();
		searchText.setPromptText("Rechercher");

		clearButton = new Button();
		clearButton.setVisible(false);

		getStyleClass().add("search-box");
		
		getChildren().addAll(searchText, clearButton);

		clearButton.setOnAction(this::doClear);
		
	
		clearButton.visibleProperty().bind(searchText.textProperty().isNotEmpty());
        searchText.prefWidthProperty().bind(widthProperty());
        clearButton.layoutXProperty().bind(widthProperty().subtract(clearButton.widthProperty()));
	}

	public StringProperty textProperty() {
		return searchText.textProperty();
	}
	
	private void doClear(ActionEvent event) {
        searchText.clear();
        searchText.requestFocus();
    }

}
