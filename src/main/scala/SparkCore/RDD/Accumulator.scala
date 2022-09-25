package SparkCore.RDD

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: Accumulator
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/25 20:56
 * */
object Accumulator {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test")
    val sc = new SparkContext(conf)
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    // 声明累加器
    val sum = sc.longAccumulator("sum")
    rdd1.foreach(num => {
      sum.add(num)
    })
    println("sum = " + sum.value)
  }

}
