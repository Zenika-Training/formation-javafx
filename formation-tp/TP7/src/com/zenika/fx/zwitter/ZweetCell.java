package com.zenika.fx.zwitter;

import com.zenika.fx.zwitter.model.Zweet;
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


				if (empty || null == item) {
					setGraphic(null);
					return;
				}

				boolean isNew = item.isNew();
				if (null == zweetPane) {
					zweetPane = new ZweetPane(item);
				}
				zweetPane.setZweet(item);
				//zweetPane.prefWidthProperty().bind(zweetListView.widthProperty().subtract(74d));

				setGraphic(zweetPane);

				if (isNew) {
					MainController.createTransition(zweetPane).play();
				}

			}
		};
		return cell;
	}

}
