package HWJS

import java.io.ByteArrayOutputStream
import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
/**
 * @ClassName: test3
 * @Auther: SDY
 * @Description:
 * @Date: 2022/12/4 14:10
 * */
object test3 {
  def main(args :Array[String]): Unit = {
    val outCapture = new ByteArrayOutputStream
    val stdout = System.out
    try {
      var line: String = null
      while ({line = StdIn.readLine(); line != null}) {
        fun1(line)
      }
    } catch {
      case e: Exception => {
        System.setOut(stdout)
        e.printStackTrace
      }
    }
  }

  def fun1(lines:String): Unit ={
    val result = mutable.Map[String,mutable.ListBuffer[Int]]()
    for(i <- 0 until(lines.toInt)){
      val input = StdIn.readLine()
      val datas = input.split(" ")
      val values: ListBuffer[Int] = result.getOrElseUpdate(datas(0), mutable.ListBuffer[Int]())
      values.append(datas(1).toInt)
      result.update(datas(0),values)
    }
    result.toList.sortBy(_._1.toInt).foreach(x => println(x._1 + " " + x._2.sum))

  }

}
