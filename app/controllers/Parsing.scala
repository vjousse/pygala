package pygala
package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import com.codahale.jerkson.Json.generate

import scalaz.effects._

object Parsing extends PygalaController {

  def highlight(api: Boolean) = Action { implicit request ⇒
    env.codeForm.bindFromRequest.fold(
      formWithErrors ⇒ BadRequest({
        for { error ← formWithErrors.errors } yield (error.message)
      } mkString "\n"),
      value ⇒ env.codeParser.colorCode(value._1, value._2).fold(
        error ⇒ BadRequest(error),
        code ⇒ api match {
          case true  ⇒ Ok(code.unsafePerformIO).as(HTML)
          case false ⇒ Ok(views.html.colored(code.unsafePerformIO))
        }))
  }

  def markdown = Action {
    implicit request ⇒
      {
        env.markdownParser.parse(request.body.asText.getOrElse("")).fold(
          error ⇒ BadRequest(error),
          markdown ⇒ Ok(markdown.unsafePerformIO).as(HTML))
      }
  }

  def lexers = Action {
    JsonOk(env.supportedFormats)
  }
}
