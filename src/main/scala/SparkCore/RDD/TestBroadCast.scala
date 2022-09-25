package SparkCore.RDD

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: TestBroadCast
 * @Auther: SDY
 * @Description:
 * @Date: 2022/9/25 21:15
 * */
object TestBroadCast {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test")
    val sc = new SparkContext(conf)
    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("d", 4)), 4)
    val list = List( ("a",4), ("b", 5), ("c", 6), ("d", 7) )
    val broadcast: Broadcast[List[(String, Int)]] = sc.broadcast(list)
    rdd1.map{
      case (x,y) => {
        var num2 = 0
        // 使用广播变量
        for((k,v) <- broadcast.value){
          if(k == x){
            num2 = v
          }
        }
        (x,(y,num2))
      }
    }.foreach(println)
  }

}
