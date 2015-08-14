
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/StevenMartel/workspaceJp/sample-test/conf/routes
// @DATE:Fri Aug 14 07:57:26 PDT 2015

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  ApplicationController_3: controllers.ApplicationController,
  // @LINE:7
  CompaniesController_2: controllers.CompaniesController,
  // @LINE:8
  AuthenticationController_4: controllers.AuthenticationController,
  // @LINE:9
  UsersController_0: controllers.UsersController,
  // @LINE:13
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    ApplicationController_3: controllers.ApplicationController,
    // @LINE:7
    CompaniesController_2: controllers.CompaniesController,
    // @LINE:8
    AuthenticationController_4: controllers.AuthenticationController,
    // @LINE:9
    UsersController_0: controllers.UsersController,
    // @LINE:13
    Assets_1: controllers.Assets
  ) = this(errorHandler, ApplicationController_3, CompaniesController_2, AuthenticationController_4, UsersController_0, Assets_1, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, ApplicationController_3, CompaniesController_2, AuthenticationController_4, UsersController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.ApplicationController.health()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/companies/events""", """controllers.CompaniesController.events(q:models.requests.EventsRequest)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/auth/login""", """controllers.AuthenticationController.login(q:models.requests.LoginRequest)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/users/events""", """controllers.UsersController.events(q:models.requests.EventsRequest)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/users/reserve""", """controllers.UsersController.reserve(q:models.requests.ReservationRequest)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_ApplicationController_health0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_ApplicationController_health0_invoker = createInvoker(
    ApplicationController_3.health(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "health",
      Nil,
      "GET",
      """ Home page""",
      this.prefix + """"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_CompaniesController_events1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/companies/events")))
  )
  private[this] lazy val controllers_CompaniesController_events1_invoker = createInvoker(
    CompaniesController_2.events(fakeValue[models.requests.EventsRequest]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CompaniesController",
      "events",
      Seq(classOf[models.requests.EventsRequest]),
      "POST",
      """""",
      this.prefix + """api/companies/events"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_AuthenticationController_login2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/auth/login")))
  )
  private[this] lazy val controllers_AuthenticationController_login2_invoker = createInvoker(
    AuthenticationController_4.login(fakeValue[models.requests.LoginRequest]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AuthenticationController",
      "login",
      Seq(classOf[models.requests.LoginRequest]),
      "POST",
      """""",
      this.prefix + """api/auth/login"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_UsersController_events3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/users/events")))
  )
  private[this] lazy val controllers_UsersController_events3_invoker = createInvoker(
    UsersController_0.events(fakeValue[models.requests.EventsRequest]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UsersController",
      "events",
      Seq(classOf[models.requests.EventsRequest]),
      "GET",
      """""",
      this.prefix + """api/users/events"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_UsersController_reserve4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/users/reserve")))
  )
  private[this] lazy val controllers_UsersController_reserve4_invoker = createInvoker(
    UsersController_0.reserve(fakeValue[models.requests.ReservationRequest]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UsersController",
      "reserve",
      Seq(classOf[models.requests.ReservationRequest]),
      "POST",
      """""",
      this.prefix + """api/users/reserve"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/$file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_ApplicationController_health0_route(params) =>
      call { 
        controllers_ApplicationController_health0_invoker.call(ApplicationController_3.health())
      }
  
    // @LINE:7
    case controllers_CompaniesController_events1_route(params) =>
      call(params.fromQuery[models.requests.EventsRequest]("q", None)) { (q) =>
        controllers_CompaniesController_events1_invoker.call(CompaniesController_2.events(q))
      }
  
    // @LINE:8
    case controllers_AuthenticationController_login2_route(params) =>
      call(params.fromQuery[models.requests.LoginRequest]("q", None)) { (q) =>
        controllers_AuthenticationController_login2_invoker.call(AuthenticationController_4.login(q))
      }
  
    // @LINE:9
    case controllers_UsersController_events3_route(params) =>
      call(params.fromQuery[models.requests.EventsRequest]("q", None)) { (q) =>
        controllers_UsersController_events3_invoker.call(UsersController_0.events(q))
      }
  
    // @LINE:10
    case controllers_UsersController_reserve4_route(params) =>
      call(params.fromQuery[models.requests.ReservationRequest]("q", None)) { (q) =>
        controllers_UsersController_reserve4_invoker.call(UsersController_0.reserve(q))
      }
  
    // @LINE:13
    case controllers_Assets_versioned5_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_1.versioned(path, file))
      }
  }
}