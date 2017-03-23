package fileSearcher

/**
  * Created by flash on 23/03/2017.
  */
// note that the constructor param (filter) is private bcz it does not have the val keyword
class FilterChecker (filter: String) {

  def matches(content: String) = content contains filter

  def findMatchedFiles(iOObjects : List[IOObject]) =
    for (iOObject <- iOObjects
      if iOObject.isInstanceOf[FileObject]
      if matches(iOObject.name)
    )
      yield iOObject

}

// creates a singleton of the object on demand -> no need to use the new keyword
object FilterChecker {
  def apply(filter: String): FilterChecker = new FilterChecker(filter)
}