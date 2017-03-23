package fileSearcher

import java.io.File

import org.scalatest.FlatSpec

/**
  * Created by flash on 23/03/2017.
  */
class MatcherTest extends FlatSpec {

  "Matcher that is passed a file matching a filter" should
  "return a list with that file name" in {
    val matcher = new Matcher("fake", "fakePath")

    val results = matcher.execute()

    assert(results == List("fakePath"))
  }


  "Matcher using a directory containing one file matching the filter" should
  "return a list with that file name" in {
    val matcher = new Matcher("txt", new File(".\\testfiles\\").getCanonicalPath)
    val results = matcher.execute()

    assert(results == List("readme.txt"))
  }


  "Matcher which is not passed a root file location" should
  "use the current location" in {
    val matcher = new Matcher("filter")
    assert(matcher.rootLocation == new File(".").getCanonicalPath())
  }

  "Matcher with a subfolder checking maching a root location with two subtree files matching" should
  "return a list with those file names" in {
    val searchSubDirectories = true
    val matcher = new Matcher("txt", new File(".\\testfiles\\").getCanonicalPath(), searchSubDirectories)

    val results = matcher.execute()

    assert(results == List("notes.txt", "readme.txt"))
  }
}
