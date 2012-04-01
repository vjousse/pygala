package pygala
import play.api.Configuration

import pygment.Pygment

class PygalaEnv(configuration: Configuration) {

  // Use underlying config object to throw an exception if the
  // key is missing
  val pygmentBin: String = configuration.underlying.getString("pygment.bin")

  val parser = Pygment(pygmentBin)

  private def conf(key: String): Option[String] = configuration getString key
}
