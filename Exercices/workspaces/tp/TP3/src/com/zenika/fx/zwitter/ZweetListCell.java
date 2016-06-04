package com.zenika.fx.zwitter;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ZweetListCell extends ListCell<Zweet> {

	private final ListView<Zweet> zweetListView;

	private ZweetPane zweetPane;
	private ZweetPaneFullJava zweetPaneFullJava;

	public ZweetListCell(final ListView<Zweet> zweetListView) {
		this.zweetListView = zweetListView;
	}

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
			if (null == zweetPaneFullJava) {
				zweetPaneFullJava = new ZweetPaneFullJava();
			}

			zweetPaneFullJava.setZweet(item);
			// text = new Text(item.toString());
			// text.wrappingWidthProperty().bind(zweetListView.widthProperty().subtract(100));
			// setGraphic(zweetPane);
			setGraphic(zweetPane);
		}
	}

}
