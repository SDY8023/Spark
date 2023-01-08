package HWJS
import scala.collection.mutable
/**
 * @ClassName: test26
 * @Auther: SDY
 * @Description:
 * @Date: 2023/1/8 15:27
 * */
object test26 {
  def main(args: Array[String]): Unit = {
    val test1 = mutable.ListBuffer[String]()
    test1.append("1")
    test1.append("2")
    test1.append("+")
    println(test1)
    test1.-=("+")
    println(test1)
  }

}
