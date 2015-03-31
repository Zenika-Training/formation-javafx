package application;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class MyEvent extends Event {

	private static final long serialVersionUID = 8624880120993935648L;

	public static final EventType<MyEvent> BOUM = new EventType<> ( ANY , "Boum" );


	public MyEvent(Object arg0, EventTarget arg1,
				   EventType<? extends Event> arg2) {
		super(arg0, arg1, arg2);
	}

	public MyEvent(EventType<? extends Event> arg0) {
		super(arg0);
	}

}
