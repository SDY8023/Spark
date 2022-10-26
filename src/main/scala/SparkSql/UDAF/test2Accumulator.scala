package SparkSql.UDAF

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import util.MyAC
object test2Accumulator {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test1")
      .setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    val rdd1 = spark.sparkContext.makeRDD(List(("zhangsan", 20), ("lisi", 30), ("wangw", 40)))
    val myac: MyAC = new MyAC()
    spark.sparkContext.register(myac,"acc")
    val res = rdd1.foreach(x => {
      myac.add(x._2)
    })
    println(myac.value)

    spark.close()

  }

}

