package pl.scalare.main.manageds

import javax.inject.Inject

import io.dropwizard.lifecycle.Managed

class AutoclosableManaged @Inject() (val auto: AutoCloseable) extends Managed {

  override def start {}

  override def stop {
    auto.close
  }
}

