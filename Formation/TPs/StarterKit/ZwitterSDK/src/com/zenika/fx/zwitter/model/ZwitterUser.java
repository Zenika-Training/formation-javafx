package com.zenika.fx.zwitter.model;

public class ZwitterUser {

  private final String username;
  private String img;
  private String displayName;

  public ZwitterUser(String username, String profileImage, String displayName) {
    this.username = username;
    this.img = profileImage;
    this.displayName = displayName;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String profileImage) {
    this.img = profileImage;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public String toString() {
    return displayName + "(" + username + ")";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ZwitterUser other = (ZwitterUser) obj;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }
}
