package dao;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import exceptions.AlreadyReservedException;
import exceptions.NotReservedException;
import models.serialdata.Attends;
import play.db.ebean.Transactional;

public class ReservationDao {
	private static final String DELETE_QUERY = "DELETE FROM attends WHERE user_id = :userId AND event_id = :eventId";

	private static Attends findReservation(int userId, int eventId) {
		return Attends.find.where().eq("userId", userId).eq("eventId", eventId).findUnique();
	}

	/**
	 * Reserve an event for specified user.
	 * 
	 * @param eventId
	 *            event to reserve
	 * @return a string message
	 * @throws AlreadyReservedException
	 */
	@Transactional
	public static void reserveEvent(int userId, int eventId) throws AlreadyReservedException {
		Attends attends = findReservation(userId, eventId);
		if (attends == null) {
			Ebean.save(new Attends(userId, eventId));
		} else {
			throw new AlreadyReservedException();
		}
	}

	/**
	 * Unreserve an event for specified user.
	 * 
	 * @param eventId
	 *            event to unreserve
	 * @return a string message
	 * @throws NotReservedException
	 */
	@Transactional
	public static void unreserveEvent(int userId, int eventId) throws NotReservedException {
		Attends attends = findReservation(userId, eventId);
		if (attends != null) {
			SqlUpdate update = Ebean.createSqlUpdate(DELETE_QUERY).setParameter("userId", userId)
					.setParameter("eventId", eventId);
			update.execute();
		} else {
			throw new NotReservedException();
		}
	}

}
