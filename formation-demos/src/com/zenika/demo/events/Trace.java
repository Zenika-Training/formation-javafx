package com.zenika.demo.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.event.*;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Trace {

	public static void register(Node n) {

		EventHandler<Event> handler =
				(Event e) -> { System.out.println("HANDLER " + e + " @ "+n); };
		EventHandler<Event> filter =
				(Event e) -> { System.out.println("FILTER  " + e + " @ "+n); };


		n.addEventHandler(MyEvent.BOUM, handler);
		n.addEventFilter(MyEvent.BOUM, filter);
		n.addEventHandler(ActionEvent.ACTION, handler);
		n.addEventFilter(ActionEvent.ACTION, filter);

	}

	public static void register(Stage n) {

		EventHandler<Event> handler =
				(Event e) -> { System.out.println("HANDLER " + e + " @ "+n); };
		EventHandler<Event> filter =
				(Event e) -> { System.out.println("FILTER  " + e + " @ "+n); };


		n.addEventHandler(MyEvent.BOUM, handler);
		n.addEventFilter(MyEvent.BOUM, filter);
		n.addEventHandler(ActionEvent.ACTION, handler);
		n.addEventFilter(ActionEvent.ACTION, filter);

	}



}
