package controllers;

import java.util.Collections;

import dao.EventsDao;
import exceptions.AuthenticationException;
import exceptions.BadRequestException;
import exceptions.ExpiredTokenException;
import models.data.CustomCodes;
import models.data.CustomMessages;
import models.data.GroupId;
import models.requests.EventsRequest;
import models.responses.CodeResponse;
import models.responses.EventsResponse;
import models.serialdata.AuthenticationToken;
import models.serialdata.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class CompaniesController extends Controller {
	/**
	 * Companies can view their own events and number of attendees with this.
	 */
	public Result events(EventsRequest query) {
		query.bind(null, request().body().asFormUrlEncoded());
		try {
			query.validate(true);

			// Authenticate
			User company = AuthenticationToken.authenticate(query.getToken(), Collections.singleton(GroupId.COMPANY));

			return Results.ok(Json.toJson(
					new EventsResponse(CustomCodes.SUCCESS_CODE, EventsDao.findEventsForCompanyOnOrAfterDate(company.getId(),
							query.getFrom(), query.getOffset(), query.getLimit()))));
		} catch (ExpiredTokenException e1) {
			return CodeResponse.generateUnauthorized(CustomMessages.EXPIRED_TOKEN);
		} catch (AuthenticationException e1) {
			return CodeResponse.UNAUTHORIZED;
		} catch (BadRequestException e1) {
			return CodeResponse.generateBadRequest(e1.getMessage());
		}
	}
}
