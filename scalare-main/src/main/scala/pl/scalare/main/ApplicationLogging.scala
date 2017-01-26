package pl.scalare.main

import com.typesafe.scalalogging.LazyLogging

import io.dropwizard.Application

trait ApplicationLogging[C <: io.dropwizard.Configuration ] extends Application[C] with LazyLogging {
  
}