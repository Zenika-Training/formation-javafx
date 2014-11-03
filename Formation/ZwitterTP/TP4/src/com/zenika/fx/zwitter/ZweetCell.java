package com.zenika.fx.zwitter;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.animation.Transition;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ZweetCell implements Callback<ListView<Zweet>, ListCell<Zweet>> {

	@Override
	public ListCell<Zweet> call(final ListView<Zweet> zweetListView) {
		final ListCell<Zweet> cell = new ListCell<Zweet>() {

			private ZweetPane zweetPane;

			@Override
			public void updateItem(final Zweet item, final boolean empty) {
				super.updateItem(item, empty);
				if (!isEmpty()) {
					if (null == zweetPane) {
						zweetPane = new ZweetPane(item);
						zweetPane.prefWidthProperty().bind(zweetListView.widthProperty().subtract(74d));
					} else {
						zweetPane.setZweet(item);
					}
					setGraphic(zweetPane);

					if (!zweetListView.getItems().isEmpty()) {
						final Zweet zweet = zweetListView.getItems().get(0);
						if (zweet == item) {
							final Transition transition = MainController.createTransition(zweetPane);
							transition.playFromStart();
						}
					}
				}
			}
		};
		return cell;
	}

}
