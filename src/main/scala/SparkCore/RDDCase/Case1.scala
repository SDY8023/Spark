package SparkCore.RDDCase

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

import scala.collection.mutable

case class UserVisitAction(
                            date: String,//用户点击行为的日期
                            user_id: Long,//用户的 ID
                            session_id: String,//Session 的 ID
                            page_id: Long,//某个页面的 ID
                            action_time: String,//动作的时间点
                            search_keyword: String,//用户搜索的关键词
                            click_category_id: Long,//某一个商品品类的 ID
                            click_product_id: Long,//某一个商品的 ID
                            order_category_ids: String,//一次订单中所有品类的 ID 集合
                            order_product_ids: String,//一次订单中所有商品的 ID 集合
                            pay_category_ids: String,//一次支付中所有品类的 ID 集合
                            pay_product_ids: String,//一次支付中所有商品的 ID 集合
                            city_id: Long
                          )//城市 id
object Case1 {
  var spark: SparkContext = null
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test1")
    spark = new SparkContext(conf)
    val data: RDD[UserVisitAction] = readData()
    test1(data).foreach(println)

  }

  def readData(): RDD[UserVisitAction] ={
    val fileRdd = spark.textFile("D:\\study\\code\\Spark\\src\\main\\resources\\user_visit_action.csv")
    fileRdd.map(x => {
      val datas = x.split(",")
      UserVisitAction(datas(0),datas(1).toLong,datas(2),datas(3).toLong,datas(4),datas(5),datas(6).toLong,datas(7).toLong,datas(8),datas(9),datas(10),
        datas(11),datas(12).toLong)
    })
  }

  /**
   * 分别统计每个品类点击的次数，下单的次数和支付的次数：
   */
  def test1(data:RDD[UserVisitAction]): RDD[(Long, (BigInt, Int, Int))] ={
    val groupData: RDD[(Long, Iterable[UserVisitAction])] = data.groupBy(_.click_category_id)
    val result: RDD[(Long, (BigInt, Int, Int))] = groupData.map(x => {
      val category_id = x._1
      var click_count: BigInt = 0
      val order_category_count: mutable.Set[String] = mutable.Set[String]()
      val pay_category_count: mutable.Set[String] = mutable.Set[String]()
      x._2.map(y => {
        click_count += 1
        order_category_count.add(y.order_category_ids)
        pay_category_count.add(y.pay_category_ids)
      })
      (category_id, (click_count, order_category_count.size, pay_category_count.size))
    })
    result
  }

}
