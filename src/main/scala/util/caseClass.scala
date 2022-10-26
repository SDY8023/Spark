package util

object caseClass {
  case class User(){
    var id:Int = _
    var name:String = _
    var age:Int = _

  }

  case class User01(){
    var userName:String = _
    var age:Int = _

  }

  // 缓存数据类型
  case class AgeBuffer(var sum:Long,var count:Long)

}
