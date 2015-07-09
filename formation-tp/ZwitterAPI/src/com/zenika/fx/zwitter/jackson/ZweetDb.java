package com.zenika.fx.zwitter.jackson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.model.ZwitterUser;

public class ZweetDb {

    private final List<Zweet> db;
    private final List<ZwitterUser> users;

    @JsonCreator
    private ZweetDb(@JsonProperty("users") final List<ZwitterUser> users, @JsonProperty("db") final List<Zweet> db) {
        this.db = db;
        this.users = users;
    }

    public List<Zweet> getDb() {
        return db;
    }

    public List<ZwitterUser> getUsers() {
        return users;
    }
}