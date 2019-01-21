package Twitter

import Twitter.TwitterAnalyse._
import org.apache.spark.streaming.dstream.DStream
import org.scalatest.FlatSpec
import twitter4j._

import scala.io.Source

class TwitterAnalyseTest extends FlatSpec {

  //Input
  val reply = Source.fromFile("DatasetTest/Reply").getLines.mkString
  val replyDStream = (reply,"reply").asInstanceOf[DStream[(Status, String)]]

  val originalTweet = Source.fromFile("DatasetTest/OriginalTweet").getLines.mkString
  val originalTweetDStream = (originalTweet,"original tweet").asInstanceOf[DStream[(Status, String)]]

  val retweet = Source.fromFile("DatasetTest/Retweet").getLines.mkString
  val retweetDStream = (retweet,"RT").asInstanceOf[DStream[(Status, String)]]


  //Output
  val replyExpected = Source.fromFile("DatasetTest/ReplyExpected").getLines.mkString(";")
  val replyExpectedDStream = replyExpected.asInstanceOf[DStream[String]]

  val originalTweetExpected = Source.fromFile("DatasetTest/OriginalTweetExpected").getLines.mkString(";")
  val originalTweetExpectedDStream = originalTweetExpected.asInstanceOf[DStream[String]]

  val retweetExpected = Source.fromFile("DatasetTest/RetweetExpected").getLines.mkString(";")
  val retweetExpectedDStream = retweetExpected.asInstanceOf[DStream[String]]


  //Test
  "tweetsWithStatus" should "return status with type of tweet" in {
    assert(tweetsWithStatus(replyDStream) === replyExpectedDStream)
  }

  "tweetsWithStatus" should "return status with type of tweet" in {
    assert(tweetsWithStatus(originalTweetDStream) === originalTweetExpected)
  }

  "tweetsWithStatus" should "return status with type of tweet" in {
    assert(tweetsWithStatus(retweetDStream) === retweetExpectedDStream)
  }

}
