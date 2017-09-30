package pl.scalare.rest.asap

import pl.scalare.spec.GrayScalarSpec

class DatabaseAppScalarSpec extends GrayScalarSpec{
  describe("A DatabaseAsap") {
    it("when invoke main") {
      DatabaseAsap.main(null)
    }
  }
}
