package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: CreateRDD
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/12 21:28
 **/
object CreateRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("createRDD")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    // 从集合中创建RDD
    //createRDDFromList(sc)
    // 从外部存储（文件）创建 RDD
    createRDDFromFile(sc)


  }

  def createRDDFromList(sc:SparkContext): Unit ={
    val rdd1 = sc.parallelize(List(1, 2, 3, 4))
    val rdd2 = sc.makeRDD(List(1,2,3,4))
    rdd1.collect().foreach(println)
    rdd2.collect().foreach(println)
    sc.stop()
  }

  def createRDDFromFile(sc:SparkContext) = {
    val fileRDD = sc.textFile("D:\\study\\code\\Spark\\src\\main\\resources\\test1.txt")
    fileRDD.foreach(println)
    sc.stop()
  }

}
