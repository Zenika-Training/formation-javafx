package com.zenika.fx.zwitter;

import java.util.List;
import java.util.Set;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class SearchService extends Service<Set<Zweet>> {

	private final StringProperty searchTextProperty = new SimpleStringProperty();

	public String getSearchText() {
		return searchTextProperty.get();
	}

	public void setSearchText(final String text) {
		searchTextProperty.set(text);
	}

	public StringProperty searchTextProperty() {
		return searchTextProperty;
	}

	private final ObjectProperty<List<Zweet>> sourceProperty = new SimpleObjectProperty<>();

	public List<Zweet> getSource() {
		return sourceProperty.get();
	}

	public void setSource(final List<Zweet> source) {
		sourceProperty.set(source);
	}

	public ObjectProperty<List<Zweet>> sourceProperty() {
		return sourceProperty;
	}

	@Override
	protected Task<Set<Zweet>> createTask() {
		// capturer la valeur courante du paramétrage du service via constructeur
		return new SearchTask(getSource(), getSearchText());
	}
}
