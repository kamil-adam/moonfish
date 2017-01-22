package pl.scalare.dropwizard.core

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.setup.Environment
import javax.validation.constraints.Min
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.Max
import io.dropwizard.lifecycle.Managed
import scala.beans.BeanProperty

trait MessageQueueFactory {

  @BeanProperty
  @NotEmpty
  var host: String

  @BeanProperty
  @Min(1)
  @Max(65535)
  var port = 5672;

//  def build(environment: Environment): MessageQueueClient = {
//    val client = new MessageQueueClient(getHost(), getPort());
//    environment.lifecycle().manage(new Managed() {
//
//      def start() {
//      }
//
//      def stop() {
//        client.close();
//      }
//    });
//    return client;
//  }

}