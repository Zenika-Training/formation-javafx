package com.zenika.fx.zwitter.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.model.ZwitterUser;

class ZweetDb {

  private final List<Zweet> db;
  private List<ZwitterUser> users;

  @JsonCreator
  private ZweetDb(@JsonProperty("users") List<ZwitterUser> users, @JsonProperty("db") List<Zweet> db) {
    this.db = db;
    this.users = users;
  }

  public List<Zweet> getDb() {
    return db;
  }

  public List<ZwitterUser> getUsers() {
    return this.users;
  }
}