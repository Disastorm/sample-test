package models.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import models.serialdata.Event;

@JsonInclude(Include.ALWAYS)
public class EventsResponse extends CodeResponse {

	@JsonProperty
	private List<Event> events;

	public EventsResponse(int code, List<Event> events) {
		super(code);
		this.events = events;
	}

	/**
	 * No need for getters and setters as Play Framework automatically generates
	 * them
	 **/

}
