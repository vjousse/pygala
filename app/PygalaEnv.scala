package pygala
import play.api.Configuration

import pygment.Pygment

class PygalaEnv(configuration: Configuration) {

  val parser = new Pygment
}
