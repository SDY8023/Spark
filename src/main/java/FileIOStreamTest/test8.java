package FileIOStreamTest;

import java.io.*;

/**
 * @ClassName test8
 * @Description
 * @Author SDY
 * @Date 2022/11/5 16:57
 **/
public class test8 {
    public static void main(String[] args) {
        bufferedReaderTest();
        bufferedWriterTest();
    }

    /**
     *  缓冲字符输入流
     */
    public static void bufferedReaderTest(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\study\\NOTES\\JavaNotes\\8. 文件操作与IO流.pdf"))){
            // 定义一个字符串，用来接收每一行读取到的数据
            String line = "";
            // 循环读取数据
            while ((line = bufferedReader.readLine()) != null){
                // 将读取到的数据 输出
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 缓冲字符输出流
     */
    public static void bufferedWriterTest(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\study\\NOTES\\JavaNotes\\test1.txt"))){
            bufferedWriter.write("hello world!");
            bufferedWriter.newLine();
            bufferedWriter.write("1233445");
            bufferedWriter.write(" dfgdasfdsf");
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
