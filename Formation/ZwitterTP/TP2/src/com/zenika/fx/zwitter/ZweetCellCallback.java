package com.zenika.fx.zwitter;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ZweetCellCallback implements Callback<ListView<Zweet>, ListCell<Zweet>> {

	@Override
	public ListCell<Zweet> call(final ListView<Zweet> zweetListView) {
		final ListCell<Zweet> cell = new ListCell<Zweet>() {
			private Text text;

			@Override
			public void updateItem(final Zweet item, final boolean empty) {
				super.updateItem(item, empty);
				if (!isEmpty()) {
					text = new Text(item.toString());
					text.wrappingWidthProperty().bind(zweetListView.widthProperty().subtract(300));
					setGraphic(text);
				}
			}
		};
		return cell;
	}

}
