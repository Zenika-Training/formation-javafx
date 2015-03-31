package com.zenika.fx.zwitter;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.util.Duration;

public class MainController implements Initializable {

	@FXML
	private ListView<Zweet> timeline;

	private Zwitter zwitter;

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		// ceci est un exemple d'utilisation du ZwitterSDK
		final ObservableList<Zweet> list = FXCollections.observableList(new LinkedList<>());
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

	public static Transition createTransition(final Node pane) {
		final FadeTransition fade = new FadeTransition(Duration.millis(100), pane);
		fade.setFromValue(0.5d);
		fade.setToValue(1d);

		final ScaleTransition scale1 = new ScaleTransition(Duration.millis(50d), pane);
		scale1.setFromX(0d);
		scale1.setToX(0.5);
		scale1.setFromY(0d);
		scale1.setToY(0.5);

		final ScaleTransition scale2 = new ScaleTransition(Duration.millis(50d), pane);
		scale2.setFromX(0.5);
		scale2.setFromY(0.5);
		scale2.setToX(1d);
		scale2.setToY(1d);
		scale2.setCycleCount(3);
		scale2.setAutoReverse(true);

		return new ParallelTransition(fade, new SequentialTransition(scale1, scale2));
	}

}
