package models.serialdata;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table
public class Attends extends Model {
	private int userId;
	private int eventId;
	private ZonedDateTime reservedAt;

	public Attends(int userId, int eventId){
		this.userId = userId;
		this.eventId = eventId;
		this.reservedAt = ZonedDateTime.now();
	}
	
	public static Finder<Long, Attends> find = new Finder<Long, Attends>(Attends.class);

	/**
	 * No need for getters and setters as Play Framework automatically generates
	 * them
	 **/

}
