package SparkSql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import util.caseClass.User

object rddToDataFrameToDataSet {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test1")
    val spark = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    import spark.implicits._
    spark.sparkContext.setLogLevel("ERROR")
    // 读取文件创建dataFrame
    val df = spark.read.json("D:\\development tool\\code\\studyTest\\src\\main\\resources\\test1.json")
    df.show(false)
    // SQL风格语法
    df.createOrReplaceTempView("user")
    spark.sql(
      s"""
         |select avg(age)
         |from user
         |""".stripMargin).show(false)
    // DSL风格语法
    df.select("username").show(false)
    // RDD
    val rdd1: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "zhangsan", 30), (2, "lisi", 20), (3, "wangwu", 40)))
    // RDD -> DATAFRAME
    val df1 = rdd1.toDF("id", "name", "age")
    df1.show()
    // DataSet
    val ds1: Dataset[User] = df1.as[User]
    ds1.show()
    // DataSet => DataFrame => RDD
    val df2: DataFrame = ds1.toDF()
    val rdd2: RDD[Row] = df2.rdd
    rdd2.foreach(x => println(x.getString(1)))
    // RDD => DataSet
    //    val ds3: Dataset[User] = rdd2.map {
    //      case (id, name, age) => User()
    //    }.toDS()
    spark.stop()


}
