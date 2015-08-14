
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/StevenMartel/workspaceJp/sample-test/conf/routes
// @DATE:Fri Aug 14 04:17:02 PDT 2015


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
