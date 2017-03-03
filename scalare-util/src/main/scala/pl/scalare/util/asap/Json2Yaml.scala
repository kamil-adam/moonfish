package pl.scalare.util.asap

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import pl.scalare.util.AppLogging

import scala.util.{Failure, Success, Try}
import scala.util.control.Exception._

class Json2Yaml extends AppLogging {
  private def apply(jsonString :String) :String  = {
      val jsonNodeTree = new ObjectMapper().readTree(jsonString)
      new YAMLMapper().writeValueAsString(jsonNodeTree)
  }


  def applyOpt(jsonString :String) :String  = {
    Option(jsonString)
      .map(applyOptNN)
      .getOrElse(null)
  }

  def applyOptNN(jsonString :String) :String = {
    val result = catching(classOf[RuntimeException]) opt {
      apply(jsonString)
    }
    result.getOrElse("#" + jsonString)
  }

  def applyEither(jsonString :String): String = {
    Option(jsonString)
      .map(applyEitherNN)
      .getOrElse(null)
  }

  def applyEitherNN (jsonString :String): String = {
    val result: Either[Throwable, String] = catching(classOf[ArithmeticException]) either {
      apply(jsonString)
    }
    result match {
      case Right(yaml) => yaml
      case Left(exception) => {
        logger.error(jsonString, exception)
        "#" + jsonString
      }
    }
  }

  def applyTry(jsonString : String): String = {
    Option(jsonString)
      .map(applyTryNN)
      .getOrElse(null)
  }

  def applyTryNN(jsonString : String): String = {
      val result : Try[String] = Try(apply(jsonString))
      result match {
        case Success(yaml) => yaml
        case Failure(exception) => {
          logger.error(jsonString, exception)
          "#" + jsonString
        }
      }

  }
}
