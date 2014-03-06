package com.zenika.fx.zwitter.model;

public class ZwitterUser {

    private final String username;
    private String img;
    private String displayName;

    public ZwitterUser(final String username, final String profileImage, final String displayName) {
        this.username = username;
        img = profileImage;
        this.displayName = displayName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(final String profileImage) {
        img = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
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
        result = prime * result + ((null == username) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZwitterUser other = (ZwitterUser) obj;
        if (null == username) {
            if (null != other.username) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }
}
