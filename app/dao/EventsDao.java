package dao;

import java.time.LocalDate;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

import models.serialdata.Event;

public class EventsDao {
	private static final String FIND_EVENTS_FOR_COMPANY_SQL_STRING = "SELECT e.id, e.name, e.start_date, e.user_id,"
			+ " count(a.user_id) as number_of_attendees"
			+ " FROM events e left outer join attends a ON e.id = a.event_id" + " GROUP BY e.id";
	private static final RawSql FIND_EVENTS_FOR_COMPANY_SQL = RawSqlBuilder.parse(FIND_EVENTS_FOR_COMPANY_SQL_STRING)
			// map result columns to bean properties
			.columnMapping("e.id", "id")
			.columnMapping("e.name", "name")
			.columnMapping("e.start_date", "startDate")
			.columnMapping("count(a.user_id)", "numberOfAttendees")
			.columnMappingIgnore("e.user_id").create();

	private static final String FIND_EVENTS_FOR_USERS_SQL_STRING = "SELECT e.id, e.name, e.start_date, u.id, u.name"
			+ " FROM events e inner join users u ON e.user_id = u.id";
	private static final RawSql FIND_EVENTS_FOR_USERS_SQL = RawSqlBuilder.parse(FIND_EVENTS_FOR_USERS_SQL_STRING)
			// map result columns to bean properties
			.columnMapping("e.id", "id")
			.columnMapping("e.name", "name")
			.columnMapping("e.start_date", "startDate")
			.columnMapping("u.id", "company.id")
			.columnMapping("u.name", "company.name").create();

	private static List<Event> findEventsOnOrAfterDate(Integer companyUserId, LocalDate from, int offset,
			int limit) {
		Query<Event> query = Ebean.find(Event.class);
		ExpressionList<Event> expression;
		if (companyUserId != null) {
			expression = query.setRawSql(FIND_EVENTS_FOR_COMPANY_SQL).where().ge("startDate", from).eq("e.user_id",
					companyUserId);
		} else {
			expression = query.setRawSql(FIND_EVENTS_FOR_USERS_SQL).where().ge("startDate", from);
		}
		return expression.orderBy("startDate ASC").setFirstRow(offset).setMaxRows(limit).findList();
	}

	/**
	 * Returns list of events and their attendees for the specified company
	 * @param companyUserId id of the company
	 * @param from return companies after this date
	 * @param offset
	 * @param limit
	 */
	public static List<Event> findEventsForCompanyOnOrAfterDate(int companyUserId, LocalDate from, int offset,
			int limit) {
		return findEventsOnOrAfterDate(companyUserId, from, offset, limit);
	}

	/**
	 * Returns a list of events and their associated companies.
	 * @param from return companies after this date
	 * @param offset
	 * @param limit
	 */
	public static List<Event> findEventsForUsersOnOrAfterDate(LocalDate from, int offset, int limit) {
		return findEventsOnOrAfterDate(null, from, offset, limit);
	}

}
