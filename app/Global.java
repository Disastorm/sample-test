
import java.lang.reflect.Method;

import com.fasterxml.jackson.databind.JsonNode;

import play.GlobalSettings;
import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

public class Global extends GlobalSettings {

    /**
     * Intercepts the {@code BadRequest}.
     * <p>
     * Called when an action has been found, but the request parsing has failed.
     * </p>
     */
    @Override
    public Promise<Result> onBadRequest(RequestHeader request, String error) {
        String message = String.format("Request: %s. Error: Bad Request. %s", request, error);
        Logger.info(message);

        return Promise.<Result> pure(Results.badRequest(message));
    }

    /**
     * Intercepts the {@code onHandlerNotFound}.
     * <p>
     * Called when no action was found to serve a request.
     * </p>
     */
    @Override
    public Promise<Result> onHandlerNotFound(RequestHeader request) {
        String message = String.format("Request: %s. Error: Action not found.", request);
        Logger.info(message);

        return Promise.<Result> pure(Results.notFound(message));
    }

    /**
     * Intercepts the {@code onRequest}.
     * <p>
     * Call to create the root Action of a request for a Java application.
     * </p>
     * <p>
     * Use this to log all incoming requests.
     * </p>
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Action onRequest(Request request, Method actionMethod) {
        JsonNode body = request.body().asJson();
        Logger.debug(String.format("\nRequest: %s\nRequest Body: %s", request,
                body));

        return super.onRequest(request, actionMethod);
    }

}
