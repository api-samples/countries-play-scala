package controllers

import play.api.libs.json._
import play.api.mvc._
import scala.io.Source

object Countries extends Controller {
  lazy val jsonData: String = Source.fromFile("countries.json", "UTF-8").getLines().mkString
  lazy val countries = Json.parse(jsonData).as[Seq[JsValue]]

  def country(cca2: String) = Action {
    countries
      .find(json => (json \ "cca2").as[String] == cca2)
      .map(Ok(_))
      .getOrElse(NotFound(s"Country not found: $cca2"))
  }
}