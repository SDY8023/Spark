package FileIOStreamTest;

import java.io.*;

/**
 * @ClassName test11
 * @Description
 * @Author SDY
 * @Date 2022/11/5 20:28
 **/
public class test11 {
    public static void main(String[] args) {

        read();
        write();
    }
    /**
     * 输入流
     */
    public static void read(){
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream("D:\\study\\NOTES\\JavaNotes\\test1.txt"),"GBK")){
            char[] array = new char[128];
            int length = 0;
            while ((length = reader.read(array)) != -1){
                System.out.println(new String(array,0,length));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 输出流
     */
    public static void write(){
        try(OutputStreamWriter gbk = new OutputStreamWriter(new FileOutputStream("D:\\study\\NOTES\\JavaNotes\\test3.txt",true),"GBK")){
            gbk.write("111111\n");
            gbk.write("afsadfadsfas");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
