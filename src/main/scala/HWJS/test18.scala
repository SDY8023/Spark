package HWJS

import scala.io.StdIn

/**
 * @ClassName: test18
 * @Auther: SDY
 * @Description:
 * @Date: 2022/12/17 15:07
 * */
object test18 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun(input)

  }
  def fun(input:String): Unit ={
    var result = -1
    // 赋予默认值
    var i:Int = 1

    while(i < input.length){
      var j:Int = 0
      var max = -1
      while(j < i){
        val str = input.substring(j, i+1)
        var a = ""
        var b = ""
        if(str.length > 1 && str.length > max){
          if(str.length % 2 == 0){
            a = str.substring(0,str.length/2)
            b = str.substring(str.length/2,str.length)
          }else{
            a = str.substring(0,str.length/2)
            b = str.substring(str.length/2+1,str.length)
          }
          if(a.equals(b.reverse)){
            max = Math.max(max,str.length)
          }
        }
        j += 1
      }
      result = Math.max(max,result)
      i += 1
    }
    println(result)
  }

}
