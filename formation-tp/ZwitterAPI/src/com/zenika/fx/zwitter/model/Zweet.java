package com.zenika.fx.zwitter.model;

public class Zweet {

    private final ZwitterUser source;
    private final String text;
    private final long timestamp = System.currentTimeMillis();
    
    public Zweet(final ZwitterUser source, final String text) {
        this.source = source;
        this.text = text;
    }

    public Zweet(final Zweet other) {
        this.source = other.source;
        this.text = other.text;
    }

    
    public ZwitterUser getSource() {
        return source;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text + "\n\t" + source;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((null == source) ? 0 : source.hashCode());
        result = prime * result + ((null == text) ? 0 : text.hashCode());
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
        final Zweet other = (Zweet) obj;
        if (null == source) {
            if (null != other.source) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        if (null == text) {
            if (null != other.text) {
                return false;
            }
        } else if (!text.equals(other.text)) {
            return false;
        }
        return true;
    }
    
    public boolean isNew() {
    	return System.currentTimeMillis() - timestamp <400;
    }
}
