package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @ClassName: RDDSerializable
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/25 18:56
 * */
object RDDSerializable {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("test1")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(Array("hello world", "hello spark", "hive", "atguigu"))
    val search = new Search("hello")
    search.getMatch1(rdd1).collect().foreach(println)
    search.getMatch2(rdd1).collect().foreach(println)
    sc.stop()

  }

}

class Search(query:String) extends Serializable {
  def isMatch(s:String):Boolean = {
    s.contains(query)
  }

  // 函数序列化案例
  def getMatch1(rdd:RDD[String]):RDD[String] = {
    rdd.filter(isMatch)
  }

  def getMatch2(rdd:RDD[String]):RDD[String] = {
    rdd.filter(x => x.contains(query))
  }
}
