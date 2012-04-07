package pygala
import play.api.Configuration

import pygment.Pygment
import pygment.Markdown

class PygalaEnv(configuration: Configuration) {

  // Use underlying config object to throw an exception if the
  // key is missing
  val pygmentBin: String = configuration.underlying.getString("pygment.bin")

  val markdownBin: String = configuration.underlying.getString("markdown.bin")

  val codeParser = Pygment(pygmentBin)
  val markdownParser = Markdown(markdownBin)

  val supportedFormats:Map[String, String] = codeParser.supportedFormats.unsafePerformIO

  private def conf(key: String): Option[String] = configuration getString key
}
