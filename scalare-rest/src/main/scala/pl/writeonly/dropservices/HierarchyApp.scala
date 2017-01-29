package pl.writeonly.dropservices;

import com.google.common.eventbus._;

object HierarchyApp extends App {

  val eb = new EventBus();
  eb.register(new IntListener());
  eb.register(new NumberListener());
  eb.post(1);
  eb.post(2L);
}

class IntListener {

  @Subscribe
  def handleEvent(i: Integer) {
    println("Integer " + i);
  }

}
class NumberListener {

  @Subscribe
  def handleEvent(i: Number) {
    println("Number " + i);
  }

}
