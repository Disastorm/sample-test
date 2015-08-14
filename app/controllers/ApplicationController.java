package controllers;

import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;

public class ApplicationController extends Controller {

	public Result health() {
		// check db connection is functioning
		DB.getDataSource();
		return ok("OK");
	}

}
