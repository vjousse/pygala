import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import pygala.pygment.Pygment

class PygmentSpec extends Specification {

  val pygment = new Pygment
  val lexerListOutput = """Pygments version 1.5, (c) 2006-2011 by Georg Brandl.

Lexers:
~~~~~~~
* Cucumber, cucumber, Gherkin, gherkin:
    Gherkin (filenames *.feature)
* abap:
    ABAP (filenames *.abap)
* ada, ada95ada2005:
    Ada (filenames *.adb, *.ads, *.ada)
* ahk:
    autohotkey (filenames *.ahk, *.ahkl)"""

  "The pygment lexer output" should {
    "be parsed into a Map" in {
      pygment.parsePygmentList(lexerListOutput) must beEqualTo(
        Map(
          "gherkin" -> "Gherkin (filenames *.feature)",
          "cucumber" -> "Gherkin (filenames *.feature)",
          "abap" -> "ABAP (filenames *.abap)",
          "ada95ada2005" -> "ABAP (filenames *.abap)",
          "ahk" -> "autohotkey (filenames *.ahk, *.ahkl)"
        )
      )
    }
  }
}
