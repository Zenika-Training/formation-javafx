package com.zenika.fx.zwitter;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class MainController implements Initializable {

    @FXML
    private ListView<Zweet> timeline;

    private Zwitter zwitter;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        // ceci est un exemple d'utilisation du ZwitterSDK
        final ObservableList<Zweet> list = FXCollections.observableList(new LinkedList<Zweet>());
        timeline.setItems(list);

        timeline.setCellFactory(new ZweetCell());

        zwitter = ZwitterBuilder.create().withObservableList(list).build();
        zwitter.start();
    }

    @FXML
    public void start() {
        zwitter.start();
    }

    @FXML
    public void stop() {
        zwitter.stop();
    }

}
