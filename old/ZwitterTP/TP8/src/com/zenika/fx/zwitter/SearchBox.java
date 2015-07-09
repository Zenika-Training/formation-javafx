package com.zenika.fx.zwitter;

import javafx.beans.property.StringProperty;
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
		searchText.prefWidthProperty().bind(widthProperty().subtract(20d));
		searchText.getStyleClass().add("search");

		clearButton = new Button();
		clearButton.setVisible(false);
		clearButton.getStyleClass().add("clearSearch");

		getChildren().addAll(searchText, clearButton);

		clearButton.setOnAction(paramT -> {
			searchText.clear();
			searchText.requestFocus();
		});

		searchText.textProperty().addListener((paramObservableValue, oldValue, newValue) -> clearButton.setVisible(null != newValue && !newValue.isEmpty()));
	}

	public StringProperty textProperty() {
		return searchText.textProperty();
	}

	@Override
	protected void layoutChildren() {
		searchText.resize(getWidth(), getHeight());
		clearButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}

}
