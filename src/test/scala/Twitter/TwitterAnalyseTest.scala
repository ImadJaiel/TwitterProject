package Twitter

import Twitter.TwitterAnalyse.statusTweets
import com.holdenkarau.spark.testing
import com.holdenkarau.spark.testing.StreamingSuiteBase
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.scalatest.FunSuite
import twitter4j.{Status, TwitterObjectFactory}

import scala.io.Source

class TwitterAnalyseTest extends FunSuite with StreamingSuiteBase {

 /* val conf = new SparkConf().setMaster("local[4]").setAppName("SparkStreaming")
  val sc = new SparkContext(conf)
  val ssc = new StreamingContext(sc, Seconds(5))
*/

  val consumerKey = "m2diMYg1KYWk3By9p6myyMZ9f"
  val consumerSecret = "PraMagseTKuJiJgbgXtgYA8vpdCWPB3b04cXrtpqOCg8lGlFSJ"
  val accessToken = "1084034236254957568-c4J3Ft1LgnLcLUdwNoXbsM9baB750H"
  val accessTokenSecret = "caGTwERV89LZ2kOoHaMbXREljp1FwmTPv9vogwxbuSDXg"
  val userid = "1084034236254957568"

  val originalTweet = Source.fromFile("DatasetTest/OriginalTweet").getLines.mkString
  val reply = Source.fromFile("DatasetTest/Reply").getLines.mkString
  val retweet = Source.fromFile("DatasetTest/Retweet").getLines.mkString
  val replyExpected = Source.fromFile("DatasetTest/ReplyExpected").getLines.toArray



  val statusReply = TwitterObjectFactory.createStatus(reply)
  val statusReplyExpected = (statusReply,"RT")

  test("really simple transformation") {

    testOperation(statusReply,)[Status,(Status,String)](statusReply, tokenize, statusReplyExpected, ordered = false)

  }



  /*"statusTweets" should "return tweet,type de tweet" in {
    statusTweets(ReceiverInputDStream[statusReply])
  }
*/

  def tokenize(f: ReceiverInputDStream[Status])  = {

    statusTweets(f)
  }

}
