package HWJS
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn
object test11 {
  def main(args: Array[String]): Unit = {
    val line = StdIn.readLine()
    val data = StdIn.readLine()
    fun1(data)

  }

  def fun1(input:String): Unit ={
    val datas: Array[String] = input.split(" ")
    val datats2: Array[Int] = datas.map(_.toInt)
    // 按照身高一个一个排序
    val resultList = mutable.ListBuffer[Int]()
    for(i <- 1 until(datats2.length-1)){
      var result:Int = 0
      var tmp:Int = 0
      // 遍历左边队列,找出最长的单调递增子序列
      var leftMin = 0
      var leftTmpMin = 0
      var j = 0
      var m = 0
      val leftList = mutable.ListBuffer[Int]()
      while(j < i){
        if(datats2(i) > datats2(m)){
          if(datats2(j) > tmp && datats2(j) < datats2(i)){
            tmp = datats2(j)
          }else{
            leftMin += 1
          }
          j += 1
          // 一轮结束
          if(j == i){
            leftList.append(leftMin)
            m += 1
            j = m
            leftTmpMin += 1
            leftMin = leftTmpMin
            tmp = 0
          }
        }else{
          leftList.append(i)
          // 直接下一轮循环
          m += 1
          j = m
          leftTmpMin += 1
          leftMin = leftTmpMin
          tmp = 0
        }

      }

      // 遍历右边队列,找出最长的单调递减子序列
      var tmpRight:Int = Integer.MAX_VALUE
      var rightMin = 0
      var tmpMin = 0
      var j2 = i+1
      var m2 = i+1
      val rightList = mutable.ListBuffer[Int]()
      while(j2 < datats2.length){
        // 若基础元素就超了，直接赋值最大
        if(datats2(m2) < datats2(i)){
            if(tmpRight > datats2(j2) && datats2(j2) < datats2(i)){
              tmpRight = datats2(j2)
            }else{
              rightMin += 1
            }
            j2 += 1
            // 进行下一轮循环
            if(j2 == datats2.length){
              rightList.append(rightMin)
              m2 += 1
              tmpMin += 1
              j2 = m2
              rightMin = tmpMin
              tmpRight = Integer.MAX_VALUE
            }
          }else{
          rightList.append(datats2.length - i -1)
          m2 += 1
          tmpMin += 1
          j2 = m2
          rightMin = tmpMin
          tmpRight = Integer.MAX_VALUE
        }
      }


      resultList.append(leftList.min + rightList.min)
    }
    println(resultList.min)

  }

}
