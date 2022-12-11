package HWJS

import HWJS.test1.fun1

import java.io.ByteArrayOutputStream
import scala.io.StdIn
/**
 * @ClassName: test2
 * @Auther: SDY
 * @Description:
 * @Date: 2022/12/4 11:53
 * */
object test2 {
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

  def fun1(d:String): Unit ={
    val data = d.split("\\.")
    if(data.size == 2){
      val d1 = data(0)
      val d2 = data(1)
      val d3 = d2.substring(0,1)
      if(d3.toInt >= 5){
        print(d1.toLong+1)
      }else{
        print(d1.toLong)
      }
    }else{
      print(d.toLong)
    }

  }

}
