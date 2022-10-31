package FileIOStreamTest;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName test6
 * @Description
 * @Author SDY
 * @Date 2022/10/31 21:41
 **/
public class test6 {
    public static void main(String[] args) {
        reader();
        writer();

    }

    /**
     * 字符输入流，读取数据
     */
    public static void reader(){
        // 字符输入流
        try(FileReader fileReader = new FileReader("D:\\study\\NOTES\\JavaNotes\\08_IO流.pdf")){
            // 实例化一个字符数组
            char[] array = new char[100];
            // 声明一个变量,用来记录每次读取到了多少个数据
            int length = 0;
            // 循环读取数据
            while ((length = fileReader.read(array)) != -1){
                String str = new String(array, 0, length);
                System.out.println(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 字符输出流，写数据
     */
    public static void writer(){
        try(FileWriter fileWriter = new FileWriter("D:\\study\\NOTES\\JavaNotes\\test1.txt",true)){
            fileWriter.write("hello world");
            // 冲刷缓冲区
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
