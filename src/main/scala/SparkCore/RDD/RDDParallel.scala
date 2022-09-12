package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: RDDParallel
 * @Auther: SDY
 * @Description: RDD 并行度与分区
 * @Date: 2022/9/12 21:40
 * */
object RDDParallel {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("createRDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(1, 2, 3, 4), 4)
    val rdd2 = sc.textFile("D:\\study\\code\\Spark\\src\\main\\resources\\test1.txt",4)
    rdd1.foreach(println)
    rdd2.foreach(println)
    sc.stop()
  }

}
