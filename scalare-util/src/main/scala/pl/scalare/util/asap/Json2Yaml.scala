package pl.scalare.util.asap

import pl.scalare.util.AppLogging

import scala.util.control.Exception._
import scala.util.{Failure, Success, Try}


trait Json2Yaml extends AppLogging {

  def applyOpt(jsonString: String): String = map(jsonString) { s =>
    val result = catching(classOf[RuntimeException]) opt {
      apply(jsonString)
    }
    result.getOrElse("#" + jsonString)
  }

  def applyEither(jsonString: String): String = map(jsonString) { s =>
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

  def applyTry(jsonString: String): String = map(jsonString) { s =>
    Try(apply(jsonString)) match {
      case Success(yaml) => yaml
      case Failure(exception) => {
        logger.error(jsonString, exception)
        "#" + jsonString
      }
    }
  }

  def map(jsonString: String)(f: String => String): String = {
    Option(jsonString)
      .map(f)
      .getOrElse(null)
  }

  protected def apply(jsonString: String): String

}
