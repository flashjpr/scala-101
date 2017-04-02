package fileSearcher

import java.io.File

import scala.None
import scala.util.control.NonFatal

/**
  * Created by flash on 23/03/2017.
  */
// note that the constructor param (filter) is private bcz it does not have the val keyword
class FilterChecker (filter: String) {

  val filterAsRegex = filter.r   // any string will attempt to become a regex using the .r method. If the regex is not
                                 // valid, a pattern syntax exception will be thrown

  def matches(content: String) = filterAsRegex.findFirstMatchIn(content) match {
    case Some (_) => true
    case None => false
  }

  def findMatchedFiles(iOObjects : List[IOObject]) =
    for (iOObject <- iOObjects
      if iOObject.isInstanceOf[FileObject]
      if matches(iOObject.name)
    )
      yield iOObject

  def findMatchedContentCount(file: File) = {

    def getFilterMatchCount (content: String) = {
      (filterAsRegex findAllIn content).length
    }

    import scala.io.Source
    try {
      val fileSource = Source.fromFile(file)      // convert Java file into Scala Source
      try {
        fileSource.getLines().foldLeft(0)(
          (accumulator, line) => accumulator + getFilterMatchCount(line))
      } catch {
        case NonFatal(_) => 0
      } finally {
        fileSource.close()
      }
    } catch {
      case NonFatal(_) => 0     // "_" -> we use the underscore to automatically throw away the except. var
    }
  }
}

// creates a singleton of the object on demand -> no need to use the new keyword
object FilterChecker {
  def apply(filter: String): FilterChecker = new FilterChecker(filter)
}