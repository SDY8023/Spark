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
    // 要删除的值33MEHOB8W0-eyJsaWNlbnNlSWQiOiIzM01FSE9COFcwIiwibGljZW5zZWVOYW1lIjoiUG9saXRla25payBNZXJsaW1hdSBNZWxha2EiLCJhc3NpZ25lZU5hbWUiOiJtYWdnaWUgc2VyIiwiYXNzaWduZWVFbWFpbCI6Im1hZ2dpZXNlckB5ZWFoLm5ldCIsImxpY2Vuc2VSZXN0cmljdGlvbiI6IkZvciBlZHVjYXRpb25hbCB1c2Ugb25seSIsImNoZWNrQ29uY3VycmVudFVzZSI6ZmFsc2UsInByb2R1Y3RzIjpbeyJjb2RlIjoiRFBOIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJEQiIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjpmYWxzZX0seyJjb2RlIjoiUFMiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6ZmFsc2V9LHsiY29kZSI6IklJIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJSU0MiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiR08iLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6ZmFsc2V9LHsiY29kZSI6IkRNIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJSU0YiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiRFMiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6ZmFsc2V9LHsiY29kZSI6IlBDIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJSQyIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjpmYWxzZX0seyJjb2RlIjoiQ0wiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6ZmFsc2V9LHsiY29kZSI6IldTIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJSRCIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjpmYWxzZX0seyJjb2RlIjoiUlMwIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJSTSIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjpmYWxzZX0seyJjb2RlIjoiQUMiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6ZmFsc2V9LHsiY29kZSI6IlJTViIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjp0cnVlfSx7ImNvZGUiOiJEQyIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjpmYWxzZX0seyJjb2RlIjoiUlNVIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJEUCIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjp0cnVlfSx7ImNvZGUiOiJQREIiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUFdTIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOnRydWV9LHsiY29kZSI6IlBTSSIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjp0cnVlfSx7ImNvZGUiOiJQUFMiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUENXTVAiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUEdPIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOnRydWV9LHsiY29kZSI6IlBQQyIsInBhaWRVcFRvIjoiMjAyMy0wNy0yNiIsImV4dGVuZGVkIjp0cnVlfSx7ImNvZGUiOiJQUkIiLCJwYWlkVXBUbyI6IjIwMjMtMDctMjYiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUFNXIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOnRydWV9LHsiY29kZSI6IlJTIiwicGFpZFVwVG8iOiIyMDIzLTA3LTI2IiwiZXh0ZW5kZWQiOnRydWV9XSwibWV0YWRhdGEiOiIwMTIwMjIwODA3TFBBQTAwNDAwOCIsImhhc2giOiIzNjE3MDA1OC8xNzI4NTUyNDotNDgxNDg2NDI3IiwiZ3JhY2VQZXJpb2REYXlzIjo3LCJhdXRvUHJvbG9uZ2F0ZWQiOmZhbHNlLCJpc0F1dG9Qcm9sb25nYXRlZCI6ZmFsc2V9-KgT7Aup6xVz89IAUiblqz3L1MhZRucjmvakY1XhQegglFthXYXNDSF0n8PSZO9eQMEcXYpAMdSbLBsM3I/GJ6ykW/MZfcX1mLSnN8UgrIGIJKhRJJ3OxRWHhE5pA3JXMHR9gnqdU2Hcb4/0gop7xEsadRA3bk4RQSWQF6xpOBisPYjN21equhyxZ5W3c5wG8ziDdcIyqLFgFxG4cdBIrKVkfQT+eX1Xr/ONDgQALXgKOJqZiSirfP/BVRTCZ2xqTf2uHHtv9/AUc/JbGxJlSHQag0doBRFZ+vLaxFxyci1P2AZSfGnPQ+zMHEoGevKnJ8xR7L5mmCVAeiCG2js5hzA==-MIIETDCCAjSgAwIBAgIBDTANBgkqhkiG9w0BAQsFADAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBMB4XDTIwMTAxOTA5MDU1M1oXDTIyMTAyMTA5MDU1M1owHzEdMBsGA1UEAwwUcHJvZDJ5LWZyb20tMjAyMDEwMTkwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDCP4uk4SlVdA5nuA3DQC+NsEnZS9npFnO0zrmMWcz1++q2UWJNuGTh0rwi+3fUJIArfvVh7gNtIp93rxjtrQAuf4/Fa6sySp4c32MeFACfC0q+oUoWebhOIaYTYUxm4LAZ355vzt8YeDPmvWKxA81udqEk4gU9NNAOz1Um5/8LyR8SGsSc4EDBRSjcMWMwMkYSauGqGcEUK8WhfplsyF61lKSOFA6VmfUmeDK15rUWWLbOMKgn2cxFA98A+s74T9Oo96CU7rp/umDXvhnyhAXSukw/qCGOVhwKR8B6aeDtoBWQgjnvMtPgOUPRTPkPGbwPwwDkvAHYiuKJ7Bd2wH7rAgMBAAGjgZkwgZYwCQYDVR0TBAIwADAdBgNVHQ4EFgQUJNoRIpb1hUHAk0foMSNM9MCEAv8wSAYDVR0jBEEwP4AUo562SGdCEjZBvW3gubSgUouX8bOhHKQaMBgxFjAUBgNVBAMMDUpldFByb2ZpbGUgQ0GCCQDSbLGDsoN54TATBgNVHSUEDDAKBggrBgEFBQcDATALBgNVHQ8EBAMCBaAwDQYJKoZIhvcNAQELBQADggIBAB2J1ysRudbkqmkUFK8xqhiZaYPd30TlmCmSAaGJ0eBpvkVeqA2jGYhAQRqFiAlFC63JKvWvRZO1iRuWCEfUMkdqQ9VQPXziE/BlsOIgrL6RlJfuFcEZ8TK3syIfIGQZNCxYhLLUuet2HE6LJYPQ5c0jH4kDooRpcVZ4rBxNwddpctUO2te9UU5/FjhioZQsPvd92qOTsV+8Cyl2fvNhNKD1Uu9ff5AkVIQn4JU23ozdB/R5oUlebwaTE6WZNBs+TA/qPj+5/wi9NH71WRB0hqUoLI2AKKyiPw++FtN4Su1vsdDlrAzDj9ILjpjJKA1ImuVcG329/WTYIKysZ1CWK3zATg9BeCUPAV1pQy8ToXOq+RSYen6winZ2OO93eyHv2Iw5kbn1dqfBw1BuTE29V2FJKicJSu8iEOpfoafwJISXmz1wnnWL3V/0NxTulfWsXugOoLfv0ZIBP1xH9kmf22jjQ2JiHhQZP7ZDsreRrOeIQ/c4yR8IQvMLfC0WKQqrHu5ZzXTH4NO3CwGWSlTY74kE91zXB5mwWAx1jig+UXYc2w4RkVhy0//lOmVya/PEepuuTTI4+UJwC7qbVlh5zfhj8oTNUXgN0AOc+Q0/WFPl1aw5VV/VrO8FCoB15lFVlpKaQ1Yh+DVU8ke+rt9Th0BCHXe0uZOEmH0nOnH/0onD
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
