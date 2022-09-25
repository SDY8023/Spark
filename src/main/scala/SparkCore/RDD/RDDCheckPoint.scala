package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: RDDCheckPoint
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/25 20:11
 * */
object RDDCheckPoint {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test1")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("D:\\study\\code\\Spark\\src\\main\\resources\\checkPoint1")
    val rdd1 = sc.textFile("D:\\study\\code\\Spark\\src\\main\\resources\\test1.txt")
    val rdd2 = rdd1.flatMap(_.split(" "))
    val rdd3 = rdd2.map(x => {
      (x, System.currentTimeMillis())
    })
    // 增加缓存
    rdd3.cache()
    rdd3.checkpoint()
    rdd3.collect().foreach(println)
    sc.stop()
  }

}
