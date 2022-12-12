package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object test10 {
  def main(args: Array[String]): Unit = {
    val input: String = StdIn.readLine()
    fun1(input)

  }

  def fun1(input:String): Unit ={
    val resultList = mutable.Map[String,Int]()
    var resultStr = mutable.ListBuffer[String]()
    for(i <- 0 until(input.length)){
      val d = input.substring(i,i+1)
      if(resultList.contains(d)){
        resultList.update(d,resultList(d)+1)
      }else{
        resultList.put(d,1)
      }
      resultStr.append(d)
    }
    val dropStr: ListBuffer[String] = mutable.ListBuffer[String]()
    var min:Int = Integer.MAX_VALUE
    resultList.foreach(x => {
      if(x._2 < min){
        min = x._2
      }
    })

    resultList.foreach(x => {
      if(x._2 == min){
        dropStr.append(x._1)
      }
    })


    dropStr.foreach(x => {
      resultStr = resultStr.filter(y => !y.equalsIgnoreCase(x))
    })
    println(resultStr.mkString(""))

  }

}
