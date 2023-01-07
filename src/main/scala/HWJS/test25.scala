package HWJS
import scala.collection.mutable
import scala.io.StdIn
object test25 {
  def main(args: Array[String]): Unit = {
    val input: String = StdIn.readLine()
    fun1(input)

  }

  def fun1(input:String): Unit ={
    val dataArray: Array[String] = input.split(" ")
    val dataList = mutable.ListBuffer[(Int,Int)]()
    // 链表长度
    val len = dataArray(0).toInt
    // 头值
    val head = dataArray(1).toInt
    for(i <- 2 until(dataArray.length-1)){
      if(i % 2 == 0){
        dataList.append((dataArray(i).toInt,dataArray(i+1).toInt))
      }
    }
    // 要删除的值
    val deleteVale = dataArray(dataArray.length-1)
    // 创建空链表
    var resultList = mutable.ListBuffer[Int]()
    resultList.append(head)
    dataList.foreach(x => {
      val d1 = x._1
      val d2 = x._2
      // 若该元素跟的值刚好是链表末尾则直接插入即可
      if(resultList.last == d2){
        resultList.append(d1)
      }else{
        // 若是中间值,则需要将该值后边的所有元素后移
        var i = 0
        var tmpList = resultList //后边的值
        var tmpList1 = mutable.ListBuffer[Int]() //前边的值
        while(i < resultList.length){
          if(resultList(i) == d2){
            tmpList1.append(resultList(i))
            tmpList.-=(resultList(i))
            i = resultList.length
          }else{
            tmpList1.append(resultList(i))
            tmpList.-=(resultList(i))
          }
        }
        tmpList1.append(d1) // 添加值
        tmpList1.foreach(d => {
          tmpList.append(d)
        })
        resultList = tmpList1
      }
    })
    println(resultList)

  }

}
