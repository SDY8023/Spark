package HWJS
import scala.collection.mutable
import scala.io.StdIn
/**
 * @ClassName: test4
 * @Auther: SDY
 * @Description:
 * @Date: 2022/12/5 19:55
 * */
object test4 {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine()
    fun1(input)
  }

  def fun1(input:String): Unit ={
    val datas: Array[String] = input.split(";")
    val xIndex = mutable.ListBuffer[Int]()
    val yIndex = mutable.ListBuffer[Int]()
    var f:String = ""
    var d:String = ""
    datas.foreach(x => {
      if(x.length > 1){
        f = x.substring(0, 1)
        d = if(x.length > 2) x.substring(1,3) else x.substring(1,2)

        f match {
          case "A" =>{
            try{
              xIndex.append(-d.toInt)
            }catch {
              case e:Exception => xIndex.append(0)
            }
          }
          case "D" =>{
            try{
              xIndex.append(d.toInt)
            }catch {
              case e:Exception => xIndex.append(0)
            }
          }
          case "S" =>{
            try{
              yIndex.append(-d.toInt)
            }catch {
              case e:Exception => yIndex.append(0)
            }
          }
          case "W" =>{
            try{
              yIndex.append(d.toInt)
            }catch {
              case e:Exception => yIndex.append(0)
            }
          }
          case _ =>
        }
      }
    })
    print(xIndex.sum,yIndex.sum)
  }

}
