package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @ClassName: test
 * @Auther: SDY
 * @Description:
 * @Date: 2022/11/10 21:33
 * */
object test3 {
  def main(args: Array[String]): Unit = {
    // 定义更新状态方法，参数values为当前批次单词频度，state为以往批次单词频度
    val updateFunc = (values:Seq[Int],state:Option[Int]) => {
      val currentCount: Int = values.foldLeft(0)(_ + _)
      val previousCount: Int = state.getOrElse(0)
      Some(currentCount + previousCount)
    }

    val conf = new SparkConf()
      .setAppName("test3")
      .setMaster("local[*]")

    val ssc = new StreamingContext(conf,Seconds(3))
    ssc.checkpoint("./ck")

    val inputStream: ReceiverInputDStream[String] = ssc.socketTextStream("bigdata03", 9999)

    val pairs: DStream[(String, Int)] = inputStream.flatMap(_.split(" ").map((_, 1)))

    // 使用updateStateByKey 来更新状态，统计从运行开始以来单词总次数
    val stateStream: DStream[(String, Int)] = pairs.updateStateByKey[Int](updateFunc)
    stateStream.print()


    ssc.start()
    ssc.awaitTermination()
  }

}
