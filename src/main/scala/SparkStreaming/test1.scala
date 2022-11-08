package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
object test1 {
  def main(args: Array[String]): Unit = {
    // 初始化配置信息
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("StreamWordCount")
    // 初始化SparkStreamingContext
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    // 监控端口创建DStream，读进来的数据为一行行
    val dataStream: ReceiverInputDStream[String] = ssc.socketTextStream("192.168.1.71", 9999)
    // 将每行数据做切分，形成一个个单词
    val wordStream: DStream[String] = dataStream.flatMap(_.split(" "))
    // 将单词映射为元组
    val wordAndOneStreams: DStream[(String, Int)] = wordStream.map(x => (x, 1))
    // 将相同的单词聚合
    val wordCountStream: DStream[(String, Int)] = wordAndOneStreams.reduceByKey(_ + _)
    // 打印
    wordCountStream.print()
    // 启动sparkStream
    ssc.start()
    ssc.awaitTermination()
  }

}
