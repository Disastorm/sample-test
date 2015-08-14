package models.serialdata;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import models.data.GroupId;

@Entity
@Table(name = "users")
@JsonInclude(Include.NON_EMPTY)
public class User extends Model {
	@Id
	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty(value = "group_id")
	private Integer groupId;

	public static Finder<Long, User> find = new Finder<Long, User>(User.class);

	/**
	 * No need for getters and setters as Play Framework automatically generates
	 * them
	 **/

	// we need these since we access it in a controller
	/**
	 * This value is specified by {@link GroupId}
	 */
	public Integer getGroupId() {
		return groupId;
	}
	
	public Integer getId() {
		return id;
	}

}
