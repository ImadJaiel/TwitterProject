package Twitter

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import twitter4j.{FilterQuery, Status}


object TwitterAnalyse {

  //Définition du SparkConf et SparkContext
  val conf = new SparkConf().setMaster("local[4]").setAppName("TwitterAnalyse")
  val sc = new SparkContext(conf)

  def main(args: Array[String]) {
    //Ne pas afficher les logs "ERROR"
    sc.setLogLevel("ERROR")

    //Création des 4 arguments d'entrée pour l'authentification
    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret, userid) = args.take(5)


    // Definitions des system properties que Twitter4j library utilise pour twitter streaming
    // On peut les utiliser pour generer les credits OAuth
    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)

    // Definition du Spark StreamingContext pour creation de DStream chaque 60 secondes
    val ssc = new StreamingContext(sc, Seconds(60))


    //Creation du filtre pour streamer sur un compte twitter precis
    val filtreQ = new FilterQuery()
    filtreQ.follow(100, userid.toLong)

    //Creation d'un ReceiverInputDStream filtrer sur un compte
    val stream = TwitterUtils.createFilteredStream(ssc, None, Some(filtreQ))


    //Utilisation de la methode statusTweets pour recuperer les tweets d'un compte
    val tweet = statusTweets(stream)

    //Utilistaion de la methode tweetsWithStatus pour definir chaque type de tweet
    val tweetStatus = tweetsWithStatus(tweet)

    //Affichage des tweets
    tweetStatus.print()

    //Stockage dans un CSV
    tweetStatus.saveAsTextFiles("tweet")

    ssc.start()
    ssc.awaitTermination()
  }

  /**
    * Cette méthode prends en paramètre le stream reçu de l'API twitter et renvoi un DStream de couple: DStream[(Status,String)]
    * Avec Status=tweet et String=Type de tweet (Original tweet ou RT ou Reply)
    * @param receiveStream: Stream reçu de l'API twitter
    * @return DStream[(Status,String)]
    */

  def statusTweets(receiveStream:ReceiverInputDStream[Status]) =

  receiveStream.map(status =>
    if (status.isRetweet) {
      (status, "RT")
    }
    else if (status.getInReplyToUserId != -1) {
      (status, "reply")
    }
    else
      (status, "original tweet")
  )

  /**Cette méthode prend en paramètre un DStream de couple: DStream[(Status,String)] et renvoi un DStream de String: DStream[String]
    * String=tweet et Type de tweet séparé par un point virgule
    * @param sTweets
    * @return DStream[String]
    */

//Transformation Dstream((a,b)) => DStream([a,b]) =>  DStream("a";"b")
  def tweetsWithStatus(sTweets: DStream[(Status,String)]) =

    sTweets.map(res => Array(res._1, res._2).mkString(";"))

}

//run m2diMYg1KYWk3By9p6myyMZ9f PraMagseTKuJiJgbgXtgYA8vpdCWPB3b04cXrtpqOCg8lGlFSJ 1084034236254957568-c4J3Ft1LgnLcLUdwNoXbsM9baB750H caGTwERV89LZ2kOoHaMbXREljp1FwmTPv9vogwxbuSDXg

