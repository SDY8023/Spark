package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object test5 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test5")
      .setMaster("local[*]")

    val ssc: StreamingContext = new StreamingContext(conf, Seconds(5))
    val inputStream: ReceiverInputDStream[String] = ssc.socketTextStream("", 9999)

  }

}
