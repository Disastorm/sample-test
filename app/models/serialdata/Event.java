package models.serialdata;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import util.CustomDateTimeSerializer;

@Entity
@Table(name = "events")
@JsonInclude(Include.NON_EMPTY)
public class Event extends Model {
	@Id
	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty(value = "start_date")
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	private LocalDateTime startDate;

	@JsonProperty
	@ManyToOne
	private User company;

	@JsonProperty(value = "number_of_attendees")
	private Integer numberOfAttendees;

	public static Finder<Long, Event> find = new Finder<Long, Event>(Event.class);

	/**
	 * No need for getters and setters as Play Framework automatically generates
	 * them
	 **/

}
