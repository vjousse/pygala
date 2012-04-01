package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  val codeForm = Form(
    tuple(
      "code" -> text,
      "lang" -> text
    )
  )

  def highlight = Action { implicit request =>
    codeForm.bindFromRequest.fold(
      formWithErrors => BadRequest("Oh, that's bad."),
      value => Ok("Code "+ value +" highlighted with lang "+ lang)
    )
  }
}
