package fileSearcher

import java.io.File

/**
  * Created by flash on 23/03/2017.
  */
trait IOObject {
  val file: File
  val name = file.getName()
}

// case classes :
//                constructor params are always public
//                companion objects are built in
case class FileObject(file: File) extends IOObject
case class DirectoryObject (file: File ) extends IOObject {
  def children() =
    try
      file.listFiles().toList map(file=> FileConverter convertToIOObject file)
    catch {
      case _ : NullPointerException => List()
    }
}
