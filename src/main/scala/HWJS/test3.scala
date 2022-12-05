package HWJS

import scala.io.StdIn
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
object test3 {
  def main(args: Array[String]): Unit = {
    val end:Int = StdIn.readInt()
    val result: ListBuffer[String] = mutable.ListBuffer[String]()
    for(i <- 0 until(end)){
      result.append(StdIn.readLine())
    }
    result.sorted.foreach(println)
  }

}
