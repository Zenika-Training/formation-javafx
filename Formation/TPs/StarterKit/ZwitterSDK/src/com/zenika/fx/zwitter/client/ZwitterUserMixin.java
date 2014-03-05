package com.zenika.fx.zwitter.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zenika.fx.zwitter.model.ZwitterUser;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
abstract class ZwitterUserMixin extends ZwitterUser {

  @JsonCreator
  public ZwitterUserMixin(@JsonProperty("username") String username,
      @JsonProperty("img") String profileImage, @JsonProperty("displayname") String displayName) {
    super(username, profileImage, displayName);
  }

  @Override
  @JsonProperty("username")
  public String getUsername() {
    return super.getUsername();
  }

  @Override
  @JsonProperty("displayname")
  public String getDisplayName() {
    return super.getDisplayName();
  }

  @Override
  @JsonProperty("img")
  public String getImg() {
    return super.getImg();
  }

}
