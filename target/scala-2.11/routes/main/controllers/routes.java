
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/StevenMartel/workspaceJp/sample-test/conf/routes
// @DATE:Fri Aug 14 07:57:26 PDT 2015

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAuthenticationController AuthenticationController = new controllers.ReverseAuthenticationController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApplicationController ApplicationController = new controllers.ReverseApplicationController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCompaniesController CompaniesController = new controllers.ReverseCompaniesController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUsersController UsersController = new controllers.ReverseUsersController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAuthenticationController AuthenticationController = new controllers.javascript.ReverseAuthenticationController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApplicationController ApplicationController = new controllers.javascript.ReverseApplicationController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCompaniesController CompaniesController = new controllers.javascript.ReverseCompaniesController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUsersController UsersController = new controllers.javascript.ReverseUsersController(RoutesPrefix.byNamePrefix());
  }

}
