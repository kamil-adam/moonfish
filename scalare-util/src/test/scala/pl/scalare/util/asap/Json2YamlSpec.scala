package pl.scalare.util.asap

import org.scalatest.{FunSpec, GivenWhenThen}

class Json2YamlSpec extends FunSpec with GivenWhenThen {

  describe("A Json2Yaml") {
    describe("when apply one-line-string") {

      val onml = new Json2Yaml()

      it("should produce null when consume null") {
        val yaml = onml.applyTry(null)
        assert(null == yaml)
      }

      it("should produce empty comment when consume empty string") {
        val yaml = onml.applyTry("")
        assert("#" == yaml)
      }
      it("should produce empty when consume empty array") {
        val yaml = onml.applyTry("[]")
        assert("--- []\n" == yaml)
      }
      it("should produce array when consume array") {
        val yaml = onml.applyTry("[0]")
        assert("---\n- 0\n" == yaml)
      }
      it("should produce object when consume empty object") {
        val yaml = onml.applyTry("{}")
        assert("--- {}\n" == yaml)
      }
      it("should produce object when consume object") {
        val yaml = onml.applyTry("{\"a\":0}")
        assert("---\na: 0\n" == yaml)
      }
    }
  }

}
