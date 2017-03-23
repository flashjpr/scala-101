package fileSearcher

import java.io.File

/**
  * Created by flash on 23/03/2017.
  */

object FileConverter {

  def convertToIOObject (file: File) =
    if (file.isDirectory()) DirectoryObject(file)
    else FileObject(file)
}
