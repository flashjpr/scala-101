package fileSearcher

import java.io.File

import org.scalatest.FlatSpec

/**
  * Created by flash on 23/03/2017.
  */
class FilterCheckerTest extends FlatSpec {

  //  Requirement #1
  "FilterChecker passed a list where one file matches the filter" should
  "return a list with that file" in {
    val listOfFiles = List(FileObject(new File("random")), FileObject(new File("match")))
    val matchedFiles = FilterChecker("match") findMatchedFiles listOfFiles
    assert(matchedFiles == List(FileObject(new File("match"))))
  }

  //  Requirement #2
  "FilterChecker passed a list with a directory that matches the filter" should
  "not return the directory" in {
    val listOfObjects = List(FileObject(new File("random")), DirectoryObject(new File("match")))
    val matchedFiles = FilterChecker("match") findMatchedFiles listOfObjects
    assert(matchedFiles.length ==  0)
  }

  "FilterChecker  passed a file with content that matches that filter" should
  "return the match succeeded" in {
    val isContentMatched = FilterChecker("mihai").matchesFileContent(new File("./testfiles/mihai.data"))
    assert(isContentMatched == true)
  }

  "FilterChecker passed a file with content that does not  match the filter" should
  "return that the match failed" in {
    val isContentMatched = FilterChecker("mihai").matchesFileContent(new File("./testfiles/readme.txt"))
    assert(isContentMatched ==  false)
  }
}
