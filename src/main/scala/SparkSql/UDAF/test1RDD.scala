package SparkSql.UDAF

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
object test1RDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test1")
      .setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    val rdd1 = spark.sparkContext.makeRDD(List(("zhangsan", 20), ("lisi", 30), ("wangw", 40)))
    val res: (Int, Int) = rdd1.map {
      case (name, age) => (age, 1)
    }.reduce((t1, t2) => {
      (t1._1 + t2._1, t1._2 + t2._2)
    })
    println(res)
    spark.close()

  }

}
