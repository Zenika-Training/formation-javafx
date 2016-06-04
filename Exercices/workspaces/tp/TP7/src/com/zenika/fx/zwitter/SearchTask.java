package com.zenika.fx.zwitter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.concurrent.Task;

public class SearchTask extends Task<Set<Zweet>> {

	private final List<Zweet> source;
	private final String keyword;

	public SearchTask(final List<Zweet> source, final String keyword) {
		this.source = source;
		this.keyword = keyword;
	}

	@Override
	protected Set<Zweet> call() throws Exception {
		final Set<Zweet> result = new HashSet<>(source.size());
		for (final Zweet zweet : source) {
			if (isCancelled()) {
				throw new RuntimeException("blop");
			}
			if (zweet.getText().contains(keyword)) {
				result.add(zweet);
			}
		}
		Thread.sleep(1000);
		return result;
	}

}
