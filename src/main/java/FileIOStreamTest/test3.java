package FileIOStreamTest;

import java.io.FileInputStream;
import java.io.IOException;

public class test3 {
    public static void main(String[] args) {
        // 建立程序与文件之间的链接
        try(FileInputStream fileInputStream = new FileInputStream("D:\\File\\yang\\study\\java\\test.txt")){
            // 读取字节流中的数据，需要有一个字节数组来读取数据
            // 数组的长度不必和文件长度一致
            byte[] array = new byte[32];
            // 声明一个整型变量，用来记录每次读取了多少个字节数据
            int length = 0;
            // 循环读取数据
            while ((length = fileInputStream.read(array)) != -1){
                // 将读取到的字节数组中的数据，转成字符串输出
                // 为了去除最后一次进行读取数据的时候，上次读取残留的问题
                // 最后一次读取的数据，只有指定部分是我们需要的数据
                String str = new String(array, 0, length);
                System.out.println(str);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
