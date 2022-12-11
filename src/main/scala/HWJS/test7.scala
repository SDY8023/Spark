package HWJS


import scala.io.StdIn

object test7 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun1(input)
  }
  def fun1(input:String): Unit ={
    if(input.length <= 8){
      println("NG")
      return
    }
    var count:Int = 0
    // 进行正则匹配
    val regex1 = "[A-Z]+".r
    val regex2 = "[a-z]+".r
    val regex3 = "[0-9]+".r
    val regex4 = "[^A-Za-z0-9]+".r
    val regexs = List(regex1, regex2, regex3, regex4)
    regexs.foreach(x => {
      val iterator = x.findAllIn(input)
      if(iterator.nonEmpty){
        count += 1
      }
    })

    if(count < 3){
      println("NG")
      return
    }
    for(i <- 0 until input.length){
      for(j <- i+1 until(input.length)){
        val str1 = input.substring(i, j)
        val str2 = input.substring(j)
        if(str1.length > 2 && str2.contains(str1)){
          println("NG")
          return
        }
      }
    }
    println("OK")
  }

}
