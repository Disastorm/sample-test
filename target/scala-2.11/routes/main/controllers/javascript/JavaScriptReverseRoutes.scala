
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/StevenMartel/workspaceJp/sample-test/conf/routes
// @DATE:Fri Aug 14 04:17:02 PDT 2015

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:13
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
  }

  // @LINE:8
  class ReverseAuthenticationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AuthenticationController.login",
      """
        function(q) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/auth/login" + _qS([(""" + implicitly[QueryStringBindable[models.requests.LoginRequest]].javascriptUnbind + """)("q", q)])})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseApplicationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def health: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ApplicationController.health",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:7
  class ReverseCompaniesController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def events: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CompaniesController.events",
      """
        function(q) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/companies/events" + _qS([(""" + implicitly[QueryStringBindable[models.requests.EventsRequest]].javascriptUnbind + """)("q", q)])})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseUsersController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def events: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UsersController.events",
      """
        function(q) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/users/events" + _qS([(""" + implicitly[QueryStringBindable[models.requests.EventsRequest]].javascriptUnbind + """)("q", q)])})
        }
      """
    )
  
    // @LINE:10
    def reserve: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UsersController.reserve",
      """
        function(q) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/users/reserve" + _qS([(""" + implicitly[QueryStringBindable[models.requests.ReservationRequest]].javascriptUnbind + """)("q", q)])})
        }
      """
    )
  
  }


}