package controllers

import play.api.libs.json.JsObject
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

class CountriesSpec extends PlaySpecification {
  "country endpoint" should {
    "get details of Finland, including capital" in new WithApplication {
      route(FakeRequest(controllers.routes.Countries.country("FI"))) match {
        case Some(response) =>
          status(response) must beEqualTo(OK)
          (contentAsJson(response) \ "capital").as[String] mustEqual "Helsinki"
        case None => failure
      }
    }

    "not find nonexistent country" in new WithApplication {
      route(FakeRequest(controllers.routes.Countries.country("XX"))) match {
        case Some(response) =>
          status(response) mustEqual NOT_FOUND
        case None => failure
      }
    }
  }
  "index endpoint" should {
    "return an array of objects in 'items'" in new WithApplication {
      route(FakeRequest(controllers.routes.Countries.index())) match {
        case Some(response) =>
          status(response) must beEqualTo(OK)
          (contentAsJson(response) \ "items").as[Seq[JsObject]].size must beGreaterThan(0)
        case None => failure
      }
    }

    "limit attributes to: name, cca2" in new WithApplication {
      route(FakeRequest(controllers.routes.Countries.index())) match {
        case Some(response) =>
          status(response) must beEqualTo(OK)
          val expectedKeySets = Seq(Set("name", "cca2"))
          val observedKeySets = (contentAsJson(response) \ "items").as[Seq[JsObject]].map(_.keys).distinct
          observedKeySets mustEqual expectedKeySets
        case None => failure
      }
    }

  }
}