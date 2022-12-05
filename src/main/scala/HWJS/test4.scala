package HWJS

import scala.io.StdIn

object test4 {
  def main(args: Array[String]): Unit = {
    val input: Int = StdIn.readInt()
    val data: String = input.toBinaryString
    var count:Int = 0
    for(i <- 0 until(data.length)){
      if(data.substring(i,i+1).equals("1")){
        count += 1
      }
    }
    print(count)
  }

}
