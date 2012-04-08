package pygala
package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import com.codahale.jerkson.Json.generate

import scalaz.effects._

object Application extends PygalaController {

  def index = Action {
    Ok(views.html.index())
  }
}
