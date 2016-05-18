package com.zenika.fx.zwitter;

import java.time.Duration;
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
		 Set<Zweet> found = new HashSet<>();

	        for (Zweet zweet : source) {
	            if (zweet.getText().toLowerCase().contains(keyword.toLowerCase())) {
	                found.add(zweet);
	            }
	        }

	        Thread.sleep(Duration.ofSeconds(2).toMillis());
	        return found;
	}

}
