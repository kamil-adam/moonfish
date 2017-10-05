package pl.scalare.rest.servlets

import org.scalatra.ScalatraServlet

class EventServlet extends ScalatraServlet {
  get("/articles/:id") {  //  <= this is a route matcher
    // this is an action
    // this action would show the article which has the specified :id
  }

  post("/articles") {
    // submit/create an article
  }

  put("/articles/:id") {
    // update the article which has the specified :id
  }

  delete("/articles/:id") {
    // delete the article with the specified :id
  }
}
