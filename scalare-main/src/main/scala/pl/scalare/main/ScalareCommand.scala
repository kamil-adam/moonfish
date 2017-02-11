package pl.scalare.main

import javax.inject.Inject

import io.dropwizard.cli.Command
import io.dropwizard.setup.Bootstrap
import net.sourceforge.argparse4j.inf.{Namespace, Subparser}


abstract class ScalareCommand @Inject()() extends Command("hello", "Prints a greeting") {

  def configure(subparser: Subparser) {
    // Add a command line option
    subparser.addArgument("-u", "--user")
      .dest("user")
      .`type`(classOf[String])
      .required(true)
      .help("The user of the program")
  }

  def run(bootstrap: Bootstrap[_], namespace: Namespace) {
    println("Hello " + namespace.getString("user"));
  }
}