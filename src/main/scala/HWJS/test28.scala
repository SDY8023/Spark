package HWJS

import scala.io.StdIn
import scala.collection.mutable
/**
 * @ClassName: test28
 * @Auther: SDY
 * @Description:
 * @Date: 2023/1/8 20:30
 * */
object test28 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun2(input)
  }

  def fun1(input:String): Unit ={
    val len = input.toInt
    val resultList = mutable.ListBuffer[String]()
    val datas = StdIn.readLine().split(" ")
    for(i <- 0 until(len)){
      resultList.append(datas(i))
    }
    val k = StdIn.readLine()
    println(datas(len-k.toInt))
  }

  def fun2(input:String): Unit ={
    val data1 = input
    val data2 = StdIn.readLine()
    // 记录操作次数
    var count:Int = 0
    val dataList1 = mutable.ListBuffer[String]()
    val dataList2 = mutable.ListBuffer[String]()
    for(i <- 0 until(data1.length)){
      dataList1.append(data1.substring(i,i+1))
    }
    for(i <- 0 until(data2.length)){
      dataList2.append(data2.substring(i,i+1))
    }
    if(dataList1.length > dataList2.length){
      // 差值
      val d = dataList1.length - dataList2.length
      dataList2.foreach(x => {
        if(!dataList1.contains(x)){
          count += 1
        }
      })
      count = count*2 + d
    }else if(dataList1.length < dataList2.length){
      // 差值
      val d = dataList2.length - dataList1.length
      dataList1.foreach(x => {
        if(!dataList2.contains(x)){
          count += 1
        }
      })
      count = count*2 + d
    }else{
      // 两者长度相等
      var len = dataList1.length
      for(i <- 0 until(len)){
        if(!dataList1(i).equalsIgnoreCase(dataList2(i))){
          count += 1
        }
      }
    }
    println(count)

  }

}
