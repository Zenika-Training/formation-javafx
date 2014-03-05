package com.zenika.fx.zwitter.search;

import java.util.List;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import com.zenika.fx.zwitter.model.Zweet;

public class SearchService extends Service<Set<Zweet>> {
    
    private StringProperty searchTextProperty = new SimpleStringProperty();
    
    public String getSearchText() { return searchTextProperty.get(); }
    public void setSearchText(String text) { searchTextProperty.set(text); }
    public StringProperty searchTextProperty() { return searchTextProperty; }

    private ObjectProperty<List<Zweet>> sourceProperty = new SimpleObjectProperty<List<Zweet>>();
    
    public List<Zweet> getSource() { return sourceProperty.get(); }
    public void setSource(List<Zweet> source) { this.sourceProperty.set(source); }
    public ObjectProperty<List<Zweet>> sourceProperty() { return this.sourceProperty; }
    
    @Override
    protected Task<Set<Zweet>> createTask() {
      // capturer la valeur courante du paramétrage du service via constructeur
      return new SearchTask(getSource(), getSearchText());
    }
}
