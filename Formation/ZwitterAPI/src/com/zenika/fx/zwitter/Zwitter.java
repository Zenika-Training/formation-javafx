package com.zenika.fx.zwitter;

import com.zenika.fx.zwitter.model.Zweet;

public interface Zwitter {

	void start();

	void stop();

	void publish(final Zweet zweet);

}
