package com.zenika.fx.zwitter.search;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.concurrent.Task;

import com.zenika.fx.zwitter.model.Zweet;

public class SearchTask extends Task<Set<Zweet>> {

  private List<Zweet> source;
  private String keyword;

  public SearchTask(List<Zweet> source, String keyword) {
    this.source = source;
    this.keyword = keyword;
  }

  @Override
  protected Set<Zweet> call() throws Exception {
    Set<Zweet> result = new HashSet<Zweet>(source.size());
    for (Zweet zweet : source) {
      Thread.sleep(100);
      if (zweet.getText().contains(keyword))
        result.add(zweet);
    }
    return result;
  }

}
