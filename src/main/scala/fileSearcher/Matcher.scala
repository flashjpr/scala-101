package fileSearcher

import java.io.File

/**
  * Created by flash on 23/03/2017.
  */
class Matcher (filter:String, rootLocation:String ) {

  val rootIOObject = FileConverter.convertToIOObject(new File(rootLocation))

  def execute() = {
    val matchedFiles = rootIOObject match {
      case file : FileObject if FilterChecker (filter) matches file.name => List(file)
      case directory : DirectoryObject => FilterChecker(filter) findMatchedFiles directory.children()
      case _ => List()
    }

    matchedFiles map(iOObject => iOObject.name)
  }
}
