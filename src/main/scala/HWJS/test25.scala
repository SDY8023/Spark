package HWJS
import javax.sound.sampled.DataLine
import scala.collection.mutable
import scala.io.StdIn
object test25 {
  def main(args: Array[String]): Unit = {
    val input: String = StdIn.readLine()
    fun2(input)

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
        var tmpList = mutable.ListBuffer[Int]() //后边的值
        resultList.foreach(x => {
          tmpList.append(x)
        })
        var tmpList1 = mutable.ListBuffer[Int]() //前边的值
        while(i < resultList.length){
          if(resultList(i) == d2){
            tmpList1.append(resultList(i))
            tmpList.-=(resultList(i))
            i = resultList.length
          }else{
            tmpList1.append(resultList(i))
            tmpList.-=(resultList(i))
            i += 1
          }
        }
        tmpList1.append(d1) // 添加值
        tmpList.foreach(d => {
          tmpList1.append(d)
        })
        resultList = tmpList1
      }
    })
    // 删除值
    val tmp1 = resultList
    tmp1.foreach(x => {
      if(x == deleteVale.toInt){
        resultList.-=(x)
      }
    })
    println(resultList.mkString(" "))

  }

  def fun2(input:String): Unit ={
    var dataList = mutable.ListBuffer[String]()
    val reg = "[0-9]"
    // 先进栈
    var i = 0
    while(i < input.length){
      var d = input.substring(i, i + 1)
      if(d.equalsIgnoreCase("-") && !input.substring(i-1, i).matches(reg)){
        d = input.substring(i, i + 2)
        i += 2
      }else if(d.matches(reg) && i != input.length-1){
        var j = i+1
        while(j < input.length){
          if(input.substring(j,j+1).matches(reg)){
            d = d + input.substring(j,j+1)
            j += 1
          }else{
            i = j
            j = input.length
          }
        }
      }else{
        i += 1
      }

      d match {
        case ")" =>{
          // 进行出栈计算
          dataList = caluate(dataList,"(")
        }
        case "]" =>{
          // 进行出栈计算
          dataList = caluate(dataList,"[")
        }
        case "}" =>{
          // 进行出栈计算
          dataList = caluate(dataList,"{")
        }
        case _ => dataList.append(d)
      }
    }
    var result = calculateValue(dataList.toList).toString
    val strings = result.split("\\.")
    if(strings(1).equalsIgnoreCase("0")){
      result = strings(0)
    }
    println(result)
  }

  def caluate(dataList:mutable.ListBuffer[String],d:String): mutable.ListBuffer[String] ={
    var cList = List[String]()
    var resultList = mutable.ListBuffer[String]()
    dataList.foreach(x => {
      resultList.append(x)
    })
    var i = resultList.length-1
    // 取需要计算的值
    while(i >= 0){
      val data = dataList(i)
      if(!data.equalsIgnoreCase(d)){
        cList = data +: cList
        resultList.remove(i)
        i -= 1
      }else{
        resultList.remove(i)
        i = -1
      }
    }
    val tmp = calculateValue(cList)
    resultList.append(tmp.toString)
    resultList
  }

  def calculateValue(aList:List[String]): Double ={
    // 计算值
    var data:Double = 0
    var cList = mutable.ListBuffer[String]()
    if(aList.length == 1){
      return aList.last.toDouble
    }
    aList.foreach(x => {
      cList.append(x)
    })
    // 先计算乘除
    val fList = List[String]("*","/")
    var j = 0
    var l = aList.length
    while(j < l){
      if(fList.contains(cList(j))){
        cList(j) match {
          case "*" => {
            data = cList(j-1).toDouble * cList(j+1).toDouble
            cList(j) = data.toString
            cList.remove(j-1)
            cList.remove(j)
            j = 0
            l = cList.length
          }
          case "/" => {
            data = cList(j-1).toDouble / cList(j+1).toDouble
            cList(j) = data.toString
            cList.remove(j-1)
            cList.remove(j)
            j = 0
            l = cList.length
          }
          case _ =>
        }
      }else{
        j += 1
      }
    }
    // 再计算加减
    j = 0
    var tmp:Double = 0
    val len = cList.length
    var tmpList = cList.toList
    if(cList.length == 1){
      return cList.last.toDouble
    }
    while(j < len){
      tmpList(j) match {
        case "+" => {
          tmp = tmpList(j-1).toDouble + tmpList(j+1).toDouble
          tmpList = tmpList.drop(j)
          tmpList = tmpList.drop(j-1)
          tmpList = tmpList.drop(j+1)
          tmpList = tmp.toString +: tmpList
          if(tmpList.length == 1){
            j = len
          }else{
            j = 1
          }
        }
        case "-" => {
          tmp = tmpList(j-1).toDouble - tmpList(j+1).toDouble
          tmpList = tmpList.drop(j)
          tmpList = tmpList.drop(j-1)
          tmpList = tmpList.drop(j+1)
          tmpList = tmp.toString +: tmpList
          if(tmpList.length == 1){
            j = len
          }else{
            j = 1
          }
        }
        case _ =>{
          j += 1
        }
      }
    }
    tmp
  }


}
