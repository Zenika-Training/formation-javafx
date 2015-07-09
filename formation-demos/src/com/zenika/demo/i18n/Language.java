package com.zenika.demo.i18n;

import java.util.Locale;

import javafx.geometry.NodeOrientation;

public enum Language {
	
	FR("fr", NodeOrientation.LEFT_TO_RIGHT),
	EN("en", NodeOrientation.LEFT_TO_RIGHT),
	AR("ar", NodeOrientation.RIGHT_TO_LEFT);

	final String localeCode;
	final Locale locale;
	final NodeOrientation nodeOrientation;
	
	Language(String lang, NodeOrientation o) {
		this.localeCode = lang;
		this.nodeOrientation = o;
		this.locale = new Locale(lang);
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getLocaleCode() {
		return localeCode;
	}
	
	public NodeOrientation getNodeOrientation() {
		return nodeOrientation;
	}
}
