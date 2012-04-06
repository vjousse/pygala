package pygala
package controllers

import play.api._
import mvc._
import data._
import http._

import com.codahale.jerkson.Json

trait PygalaController extends Controller {
  protected val env = new PygalaEnv(Play.unsafeApplication.configuration)

  def JsonOk(map: Map[String, Any]) = Ok(Json generate map) as JSON
}
