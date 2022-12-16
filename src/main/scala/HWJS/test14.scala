package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object test14 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun1(input)
  }

  def fun1(input:String): Unit ={
    val strings = input.split(" ")
    val dataList: ListBuffer[String] = mutable.ListBuffer[String]()
    val n: Int = strings(0).toInt
    for(i <- 1 to n){
      dataList.append(strings(i))
    }
    val x = strings(n+1)
    val k = strings.last.toInt

    var resultList = mutable.ListBuffer[String]()
    dataList.foreach(m => {
      if(m.length == x.length && !m.equals(x)){
        var mList = mutable.ListBuffer[String]()
        var xList = mutable.ListBuffer[String]()
        for(i <- 0 until(m.length)){
          mList.append(m.substring(i,i+1))
          xList.append(x.substring(i,i+1))
        }
        mList = mList.sorted
        xList = xList.sorted
        if(mList.mkString("").equals(xList.mkString(""))){
          resultList.append(m)
        }
      }
    })
    println(resultList.length)
    if(resultList.length >= k){
      resultList = resultList.sorted
      println(resultList(k-1))
    }
  }

}
