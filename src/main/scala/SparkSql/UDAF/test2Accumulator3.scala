package SparkSql.UDAF

import org.apache.spark.SparkConf
import org.apache.spark.sql.{SparkSession, TypedColumn}
import util.MyAveragUDAF
import util.caseClass.User01

class test2Accumulator3 {
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
    val ds1 = rdd1.toDF("userName", "age").as[User01]
    val myAverage = new MyAveragUDAF2
    val column: TypedColumn[User01, Double] = myAverage.toColumn
    ds1.select(column).show()



    spark.close()

  }

}
