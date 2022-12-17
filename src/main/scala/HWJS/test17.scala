package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
/**
 * @ClassName: test17
 * @Auther: SDY
 * @Description:
 * @Date: 2022/12/17 14:00
 * */
object test17 {
  def main(args: Array[String]): Unit = {
    val line = StdIn.readLine()
    fun1(line)
  }

  def fun1(input:String): Unit ={
    val r = "[A-Za-z]"
    val tmpList = mutable.ListBuffer[String]()
    for(i <- 0 until(input.length)){
      val a = input.substring(i,i+1)
      if(a.matches(r)){
        tmpList.append(a.trim)
      }else {
        tmpList.append(" ")
      }
    }

    var result:ListBuffer[String] = mutable.ListBuffer[String]()
    var m:Int = tmpList.length
    for(i <- 0 until(tmpList.length)){
      var t = ""
      if(tmpList(tmpList.length-i-1).equals(" ")){
        for(j <- tmpList.length-i-1 until(m)){
          t += tmpList(j)
        }
        result.append(t.replace(" ",""))
        m = tmpList.length-i-1
      }else if(i == tmpList.length-1){
        for(n <- 0 until(m)){
          t += tmpList(n)
        }
        result.append(t.replace(" ",""))
      }
    }
    println(result.mkString(" "))


  }

}
