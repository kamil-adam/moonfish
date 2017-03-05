package pl.scalare.util.asap

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import pl.scalare.util.AppLogging

object Json2YamlJackson extends AppLogging {
  new FileJson2YamlSource(new Json2YamlJackson).convert(args(0), args(1))
}

class Json2YamlJackson extends Json2Yaml {
  override protected def apply(jsonString: String): String = {
    val jsonNodeTree = new ObjectMapper().readTree(jsonString)
    new YAMLMapper().writeValueAsString(jsonNodeTree)
  }
}
