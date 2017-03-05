package pl.scalare.util.asap

import java.io._

import pl.scalare.util.asap.Control.using
import pl.scalare.util.asap.Control.toConsumerAny

import scala.io.Source

abstract class FileJson2Yaml (val onml : Json2Yaml) {
  protected def appendLine(out : Writer, line : String): Unit = {
    val yaml = onml.applyTry(line)
    out.append(yaml)
  }
  def convert(in: String, out: String): Unit = convert(new File(in), new File(out))

  def convert(in: File, out: File): Unit = convert(new FileInputStream(in), new FileOutputStream(out))

  def convert(in: InputStream, out: OutputStream): Unit

}
class FileJson2YamlSource (onml : Json2Yaml) extends FileJson2Yaml(onml){

  override def convert(in: InputStream, out: OutputStream): Unit = {
    using(new PrintWriter(out)) { pw =>
      using(Source.fromInputStream(in, "UTF-8")) { source =>
        source.getLines().foreach { line =>
          appendLine(pw, line)
        }
      }
    }
  }
}
class FileJson2YamlIO(onml : Json2Yaml) extends FileJson2Yaml(onml){

  override def convert(in: InputStream, out: OutputStream): Unit = {
    convert (new InputStreamReader(in, "UTF-8"), new OutputStreamWriter(out, "UTF-8"))
  }

  def convert(in: Reader, out: Writer): Unit = {
    using(new BufferedWriter(out)) { bw =>
      using(new BufferedReader(in)) { br =>
        convert(br, bw)
      }
    }
  }

  def convert(in: BufferedReader, out: BufferedWriter): Unit = {
    in.lines().forEach { (line: String)=>
      appendLine(out, line)
    }
  }

}
