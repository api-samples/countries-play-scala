package controllers

import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

class CountriesSpec extends PlaySpecification {
  "Countries controller" should {
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
}