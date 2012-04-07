package pygala
package util

import scalaz.effects._

import java.io.File

object FileUtil {

  def writeToTempFile(prefix: String, data: String): IO[File] = io {
    val file: File = File.createTempFile(prefix, ".tmp")
    file.deleteOnExit
    printToFile(file)(p => p.println(data))
    file
  }

  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) } finally { p.close() }
  }

}
