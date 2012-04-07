package pygala
package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import com.codahale.jerkson.Json.generate

import scalaz.effects._

object Application extends PygalaController {

  val codeForm = Form(
    tuple(
      "code" -> text,
      "lang" -> text) verifying ("Unsupported language to highlight", fields ⇒ fields match {
        case (c, l) ⇒ env.supportedFormats.get(l.toLowerCase).isDefined
      }))

  val markdownForm = Form("markdown" -> text)

  def highlight = Action { implicit request ⇒
    codeForm.bindFromRequest.fold(
      formWithErrors ⇒ BadRequest({
        for { error <- formWithErrors.errors } yield(error.message)
      } mkString "\n"),
      value ⇒ env.codeParser.colorCode(value._1, value._2).fold(
        error ⇒ BadRequest(error),
        code ⇒ Ok(code.unsafePerformIO)))
  }


  def markdown = Action { implicit request ⇒
    markdownForm.bindFromRequest.fold(
      formWithErrors ⇒ BadRequest({
        for { error <- formWithErrors.errors } yield(error.message)
      } mkString "\n"),
      value ⇒ env.markdownParser.parse(value).fold(
        error ⇒ BadRequest(error),
        markdown ⇒ Ok(markdown.unsafePerformIO)))
  }

  def lexers = Action {
    JsonOk(env.supportedFormats)
  }
}
