package controllers;

import java.util.Collections;
import java.util.List;

import dao.EventsDao;
import dao.ReservationDao;
import exceptions.AlreadyReservedException;
import exceptions.AuthenticationException;
import exceptions.BadRequestException;
import exceptions.ExpiredTokenException;
import exceptions.NotReservedException;
import models.data.CustomCodes;
import models.data.CustomMessages;
import models.data.GroupId;
import models.requests.EventsRequest;
import models.requests.ReservationRequest;
import models.responses.CodeResponse;
import models.responses.EventsResponse;
import models.responses.ReservationResponse;
import models.serialdata.AuthenticationToken;
import models.serialdata.Event;
import models.serialdata.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class UsersController extends Controller {
	/**
	 * Users can view all events and the companies that are hosting them with this api
	 */
	public Result events(EventsRequest query) {
		try {
			query.validate(false);
			List<Event> events = EventsDao.findEventsForUsersOnOrAfterDate(query.getFrom(), query.getOffset(),
					query.getLimit());
			return Results.ok(Json.toJson(new EventsResponse(CustomCodes.SUCCESS_CODE, events)));
		} catch (AuthenticationException e1) {
			// shouldn't actually happen
			return CodeResponse.UNAUTHORIZED;
		} catch (BadRequestException e1) {
			return CodeResponse.generateBadRequest(e1.getMessage());
		}
	}

	public Result reserve(ReservationRequest query) {
		query.bind(null, request().body().asFormUrlEncoded());
		try {
			query.validate();

			// Authenticate only students can reserve events
			User user;
			try {
				user = AuthenticationToken.authenticate(query.getToken(), Collections.singleton(GroupId.STUDENT));
			} catch (ExpiredTokenException e1) {
				return CodeResponse.generateUnauthorized(CustomMessages.EXPIRED_TOKEN);
			}catch (AuthenticationException e1) {
				return CodeResponse.generateUnauthorized("Only students can create reservations.");
			}

			if (query.getReserve()) {
				ReservationDao.reserveEvent(user.getId(), query.getEventId());
			} else {
				ReservationDao.unreserveEvent(user.getId(), query.getEventId());
			}

			return Results.ok(Json.toJson(new ReservationResponse(CustomCodes.SUCCESS_CODE, query.getReserve()
					? CustomMessages.SUCCESS_RESERVATION_MESSAGE : CustomMessages.SUCCESS_UNRESERVATION_MESSAGE)));
		} catch (AuthenticationException e1) {
			return CodeResponse.generateUnauthorized("Invalid or missing authentication");
		} catch (BadRequestException e1) {
			return CodeResponse.generateBadRequest(e1.getMessage());
		} catch (AlreadyReservedException | NotReservedException e) {
			return Results.ok(Json.toJson(new ReservationResponse(e.getCode(), e.getMessage())));
		}
	}
}
