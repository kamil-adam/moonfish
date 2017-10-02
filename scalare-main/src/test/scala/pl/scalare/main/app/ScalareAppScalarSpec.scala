package pl.scalare.main.app

import pl.scalare.spec.GrayScalarSpec

class ScalareAppScalarSpec extends GrayScalarSpec {

  describe("A ScalareApp") {

    it("when invoke with empty array") {
      ScalareApp.main(Array())
    }
  }
}
