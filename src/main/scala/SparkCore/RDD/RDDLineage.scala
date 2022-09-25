package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: RDDLineage
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/25 19:31
 * */
object RDDLineage {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("createRDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("D:\\study\\code\\Spark\\src\\main\\resources\\test1.txt")
    println(rdd1.dependencies)
    println("===================")
    val rdd2 = rdd1.flatMap(_.split(" "))
    println(rdd2.dependencies)
    println("===================")
    val rdd3 = rdd2.map((_, 1))
    println(rdd3.dependencies)
    println("===================")
    val rdd4 = rdd3.reduceByKey(_ + _)
    println(rdd4.dependencies)
    println("===================")
    rdd4.foreach(println)
  }

}
