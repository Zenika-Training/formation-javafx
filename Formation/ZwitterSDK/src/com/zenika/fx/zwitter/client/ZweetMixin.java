package com.zenika.fx.zwitter.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.model.ZwitterUser;

abstract class ZweetMixin extends Zweet {

    @JsonCreator
    public ZweetMixin(@JsonProperty("source") final ZwitterUser source, @JsonProperty("quote") final String text) {
        super(source, text);
    }

    @Override
    @JsonProperty("source")
    public ZwitterUser getSource() {
        return super.getSource();
    }

    @Override
    @JsonProperty("quote")
    public String getText() {
        return super.getText();
    }

}
