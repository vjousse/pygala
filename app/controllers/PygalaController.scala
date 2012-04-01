package pygala
package controllers

import play.api._

trait PygalaController {
  protected val env = new PygalaEnv(Play.unsafeApplication.configuration)
}
