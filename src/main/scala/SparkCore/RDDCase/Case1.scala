package SparkCore.RDDCase

import org.apache.commons.lang3.StringUtils
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
    val result = test1(data)
    println("====点击次数====")
    result._1.foreach(println)
    println("====下单次数====")
    result._2.foreach(println)
    println("====支付次数====")
    result._3.foreach(println)

  }

  def readData(): RDD[UserVisitAction] ={
    val fileRdd = spark.textFile("D:\\File\\yang\\code\\idea\\spark\\src\\main\\resources\\user_visit_action.csv")
    fileRdd.map(x => {
      val datas = x.split(",")
      UserVisitAction(datas(0),datas(1).toLong,datas(2),datas(3).toLong,datas(4),datas(5),datas(6).toLong,datas(7).toLong,datas(8),datas(9),datas(10),
        datas(11),datas(12).toLong)
    })
  }

  /**
   * 分别统计每个品类点击的次数，下单的次数和支付的次数：
   */
  def test1(data:RDD[UserVisitAction]): (RDD[(Long,Long)],RDD[(String,Long)],RDD[(String,Long)]) ={
    // 每个品类点击的次数
    val clickRDD: RDD[(Long, Long)] = data.filter(x => (x.click_category_id != -1 && x.click_product_id != -1))
      .groupBy(_.click_category_id).map(x => {
      var click_count: Long = 0
      x._2.foreach(d => {
        if(d.click_category_id != -1 && d.click_product_id != -1) {
          click_count += 1
        }
      })
      (x._1, click_count)
    })

    // 每个品类下单次数
    val orderRDD: RDD[(String, Long)] = data.filter(x => StringUtils.isNotEmpty(x.order_category_ids) && StringUtils.isNotEmpty(x.order_category_ids))
      .groupBy(_.order_category_ids)
      .map(x => {
        var count: Long = 0
        x._2.foreach(d => {
          if (d.order_category_ids != null && d.order_product_ids != null) {
            count += 1
          }
        })
        (x._1, count)
      })

    // 每个品类的支付次数
    val payRDD: RDD[(String, Long)] = data.filter(x => StringUtils.isNotEmpty(x.pay_category_ids) && StringUtils.isNotEmpty(x.pay_product_ids))
      .groupBy(_.pay_category_ids)
      .map(x => {
        var count: Long = 0
        x._2.foreach(d => {
          if (d.pay_category_ids != null && d.pay_product_ids != null) {
            count += 1
          }
        })
        (x._1, count)
      })
    (clickRDD,orderRDD,payRDD)
  }

}
