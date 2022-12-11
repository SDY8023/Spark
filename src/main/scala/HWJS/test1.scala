package HWJS

import java.io._
import scala.io._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks
/**
 * @ClassName: test1
 * @Auther: SDY
 * @Description:
 * @Date: 2022/12/4 10:56
 * */
object test1 {
  def main(args :Array[String]): Unit = {
    val outCapture = new ByteArrayOutputStream
    val stdout = System.out
    try {
      var line: String = null
      while ({line = StdIn.readLine(); line != null}) {
        fun1(line.toLong)
      }
    } catch {
      case e: Exception => {
        System.setOut(stdout)
        e.printStackTrace
      }
    }
  }

  def fun1(d:Long): Unit ={
    var tmp: Long = 2
    var data = d
    var sqrt = math.sqrt(d).toLong + 1
    val result = new ListBuffer[String]()
    while(data != 1 && tmp < sqrt){
      while(data % tmp == 0){
        data = data / tmp
        result.append(tmp.toString)
        result.append(" ")
      }
      tmp += 1
    }
    result.foreach(print)
    if(data !=  1){
      print(data)
    }
  }

  def fun2(d:Long): Unit ={
    var tmp:Long = 2
    var data = d
    Breaks.breakable(
      while(data != 1){
        while(data % tmp == 0){
          print(tmp+" ")
          fun2(data / tmp)
          Breaks.break()
        }
        tmp += 1
      }
    )
  }

}
