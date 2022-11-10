package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @ClassName: test4
 * @Auther: SDY
 * @Description:
 * @Date: 2022/11/10 22:00
 * */
object test4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test4")
      .setMaster("local[*]")

    val ssc = new StreamingContext(conf, Seconds(3))
    ssc.checkpoint("D:\\study\\code\\Spark\\src\\main\\scala\\SparkStreaming\\ck")

    val inputStream: ReceiverInputDStream[String] = ssc.socketTextStream("bigdata03", 9999)

    val pairs: DStream[(String, Int)] = inputStream.flatMap(_.split(" ")).map((_, 1))
    pairs.reduceByKeyAndWindow((a:Int,b:Int) => (a+b),Seconds(12),Seconds(6)).print()
    ssc.start()
    ssc.awaitTermination()
  }

}
