package com.zenika.fx.zwitter;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;

import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.model.ZwitterUser;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

public class MainController implements Initializable {

	@FXML
	private SearchBox searchBox;

	@FXML
	private ListView<Zweet> timeline;

	@FXML
	private ProgressIndicator loading;

	@FXML
	private TextArea zweetArea;

	@FXML
	private Label zweetCompletionText;

	@FXML
	private ProgressBar zweetCompletion;

	private Zwitter zwitter;

	@Override
	public void initialize(final URL url, final ResourceBundle resourceBundle) {
		final ObservableList<Zweet> list = FXCollections.observableList(new LinkedList<>());
		timeline.setItems(list);

		timeline.setCellFactory(new ZweetCell());

		zwitter = ZwitterBuilder.create().withObservableList(list).build();
		zwitter.start();

		// implement search via a Service
		final SearchService searchService = new SearchService();
		searchService.setSource(list);
		searchService.searchTextProperty().bind(searchBox.textProperty());

		final EventHandler<WorkerStateEvent> loadingTimeline = paramT -> {
			loading.setVisible(true);
			timeline.setDisable(true);
		};
		final EventHandler<WorkerStateEvent> restoreTimeline = paramT -> {
			loading.setVisible(false);
			timeline.setDisable(false);
		};

		final EventHandler<WorkerStateEvent> showResults = wse -> {
			restoreTimeline.handle(wse);
			final Set<Zweet> result = (Set<Zweet>) wse.getSource().getValue();
			final ObservableList<Zweet> foundZweets = FXCollections.observableArrayList(result);
			timeline.setItems(foundZweets);
		};
		searchService.setOnScheduled(loadingTimeline);
		searchService.setOnFailed(restoreTimeline);
		searchService.setOnCancelled(restoreTimeline);
		searchService.setOnSucceeded(showResults);

		searchBox.textProperty().addListener((obs, oldValue, newValue) -> {
			if (null != newValue && !newValue.isEmpty() && newValue.length() >= 3) {
				searchService.restart();
			} else {
				searchService.cancel();
				timeline.setItems(list);
			}
		});

		final IntegerBinding zweetLengthProperty = new IntegerBinding() {
			{
				bind(zweetArea.textProperty());
			}

			@Override
			protected int computeValue() {
				if (null == zweetArea.getText() || zweetArea.getText().isEmpty()) {
					return 0;
				}
				return zweetArea.getText().length();
			}
		};

		zweetCompletionText.textProperty().bind(zweetLengthProperty.asString());
		zweetCompletion.progressProperty().bind(zweetLengthProperty.divide(140d));

		zweetCompletion.getStyleClass().add("completion");
		zweetCompletionText.getStyleClass().add("completion");
		zweetLengthProperty.addListener((paramObservableValue, oldValue, newValue) -> {
			if (140 < newValue.intValue()) {
				zweetCompletion.getStyleClass().addAll("overflow");
				zweetCompletionText.getStyleClass().addAll("overflow");
			} else {
				zweetCompletion.getStyleClass().removeAll("overflow");
				zweetCompletionText.getStyleClass().removeAll("overflow");
			}
		});
	}

	@FXML
	public void start() {
		zwitter.start();
	}

	@FXML
	public void stop() {
		zwitter.stop();

	}

	@FXML
	public void publish() {
		final ZwitterUser zwitterUser = new ZwitterUser("", "", "");
		final Zweet zweet = new Zweet(zwitterUser, zweetArea.getText());
		zwitter.publish(zweet);
	}

	public static Transition createTransition(final Node pane) {
		final FadeTransition fade = new FadeTransition(Duration.seconds(100d), pane);
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
