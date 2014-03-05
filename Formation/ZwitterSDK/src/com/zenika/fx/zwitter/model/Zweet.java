package com.zenika.fx.zwitter.model;

public class Zweet {

  private final ZwitterUser source;
  private final String text;
  
  public Zweet(ZwitterUser source, String text) {
    super();
    this.source = source;
    this.text = text;
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
    result = prime * result + ((source == null) ? 0 : source.hashCode());
    result = prime * result + ((text == null) ? 0 : text.hashCode());
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
    Zweet other = (Zweet) obj;
    if (source == null) {
      if (other.source != null)
        return false;
    } else if (!source.equals(other.source))
      return false;
    if (text == null) {
      if (other.text != null)
        return false;
    } else if (!text.equals(other.text))
      return false;
    return true;
  }
}
