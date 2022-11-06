package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}

import scala.collection.mutable
object test2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("queueRDD")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    ssc.sparkContext.setLogLevel("ERROR")
    // 创建RDD队列
    val rddQueue: mutable.Queue[RDD[Int]] = new mutable.Queue[RDD[Int]]()
    testQueueRDD(ssc,rddQueue)
    ssc.start()
    // 循环写入队列RDD
    for(i <- 1 to 10){
      rddQueue += ssc.sparkContext.makeRDD(1 to 300,10)
      Thread.sleep(2000)
    }
    ssc.awaitTermination()

  }
  def testQueueRDD(ssc:StreamingContext,rddQueue:mutable.Queue[RDD[Int]]): Unit ={
    // 创建QueueInputStream
    val inputStream: InputDStream[Int] = ssc.queueStream(rddQueue, false)
    // 处理队列中的数据
    val dataStream1: DStream[(Int, Int)] = inputStream.map((_, 1))
    val result = dataStream1.reduceByKey(_ + _)
    result.print(1000)
  }

}
