package SparkSql.UDAF

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import util.MyAveragUDAF

object test2Accumulator2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test1")
      .setMaster("local[*]")
    val spark: SparkSession = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    val rdd1 = spark.sparkContext.makeRDD(List(("zhangsan", 20), ("lisi", 10), ("wangw", 40)))
    import spark.implicits._
    rdd1.toDF("name","age").createOrReplaceTempView("user")
    val myAverage = new MyAveragUDAF
    // 在spark中注册函数
    spark.udf.register("myAverage",myAverage)
    spark.sql(
      s"""
         |select myAverage(age)
         |from user
         |""".stripMargin).show()


    spark.close()

  }

}
