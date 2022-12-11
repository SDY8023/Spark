package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object test5 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun4(input)
  }

  def fun4(input:String): Unit ={
    val totalMoneyAndData = input.split(" ")
    val totalMoney:Int = totalMoneyAndData(0).toInt
    val data:Int = totalMoneyAndData(1).toInt
    // 记录每个产品的数据
    val dataList: ListBuffer[(Int, Int, Int, Int)] = mutable.ListBuffer[(Int, Int, Int, Int)]()
    for(i <- 0 until(data)){
      val str: String = StdIn.readLine()
      val strings1 = str.split(" ")
      dataList.append((i + 1,strings1(0).toInt,strings1(1).toInt,strings1(2).toInt))
    }
    // 记录每个主属性id -> 附属物品
    val dataMap = mutable.Map[Int,mutable.ListBuffer[(Int,Int,Int)]]()
    dataList.foreach(x => {
      if(x._4 == 0){
        dataMap.put(x._1,ListBuffer((x._2,x._3,x._4)))
      }
    })
    dataList.foreach(x => {
      if(dataMap.contains(x._4)){
        val maybeTuples: ListBuffer[(Int, Int, Int)] = dataMap.get(x._4).get
        maybeTuples.append((x._2,x._3,x._4))
        dataMap.update(x._4,maybeTuples)
      }
    })

    // 每种情况表 (商品ID,价格,满意度)
    var resultList: mutable.ListBuffer[(Int,Int, Int)] = mutable.ListBuffer[(Int,Int, Int)]()
    var totalPrice:Int = 0
    var totalIndex:Int = 0
    dataMap.foreach(x => {
      totalPrice = x._2(0)._1
      totalIndex = x._2(0)._1 * x._2(0)._2
      resultList.append((x._1,totalPrice,totalIndex))
      var j:Int = 1
      var i:Int = 1
      var tmpPrice =  totalPrice
      var tmpIndex = totalIndex
      if(x._2.length != 1){
        while(i < x._2.length){
          totalPrice += x._2(i)._1
          totalIndex += x._2(i)._1 * x._2(i)._2
          resultList.append((x._1,totalPrice,totalIndex))
          if(i == x._2.length -1){
            tmpPrice += x._2(j)._1
            tmpIndex += x._2(j)._1 * x._2(j)._2
            totalPrice = tmpPrice
            totalIndex = tmpIndex
            j += 1
            i = j
          }else{
            i += 1
            totalPrice = tmpPrice
            totalIndex = tmpIndex
          }
        }
      }
    })
    var result:Int = 0
    resultList = resultList.filter(_._1 <= totalMoney)

    var a = 0
    var b = 0
    var tmpA = 0
    var tmpB = 0
    var j = 0
    var i = 0
    val rList = mutable.ListBuffer[Int]()
    while(i < resultList.length){
      rList.append(resultList(i)._3)
      a = resultList(i)._2
      b = resultList(i)._3
      tmpA = a
      tmpB = b
      j = i
      var m = i
      while(j < resultList.length-1){
        j += 1
        var id1 = resultList(m)._1
        if(resultList(j)._1 != id1){
          a += resultList(j)._2
          b += resultList(j)._3
          if(a <= totalMoney){
            rList.append(b)
          }
        }
        if(j == resultList.length - 1){
          m += 1
          j = m
          if(id1 != resultList(m)._1){
            tmpA += resultList(j)._2
            tmpB += resultList(j)._3
          }else {
            tmpA = tmpA - resultList(j-1)._2 + resultList(j)._2
            tmpB = tmpB - resultList(j-1)._3 + resultList(j)._3
          }
          a = tmpA
          b = tmpB
        }else{
          a = tmpA
          b = tmpB
        }
      }
      i += 1

    }
    println(rList.max)

  }

}
