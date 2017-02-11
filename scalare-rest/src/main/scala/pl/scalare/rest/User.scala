package pl.scalare.rest

import java.security.Principal

import scala.beans.BeanProperty

class User(@BeanProperty val name: String) extends Principal {

}
