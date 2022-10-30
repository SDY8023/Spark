package FileIOStreamTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class test4 {
    public static void main(String[] args) {
        // 1. 实例化一个管道，连接文件和程序。
        // 对于FileOutputStream来说，如果目标文件不存在，则会自动的创建。
        // 当无法创建这个文件的时候(父级目录不存在)，创建会失败，会触发FileNotFoundException 。
        try(FileOutputStream fileOutputStream = new FileOutputStream("D:\\File\\yang\\study\\java\\test1.txt")){
            // 准备数据写入到输出流中
            String content = "abcdefg";
            // 将数据写入流中，由输出流写入到文件
            fileOutputStream.write(content.getBytes());

            // 冲刷缓冲区，将缓冲区中的数据强制流动到文件中。
            // 在流关闭的时候，会自动的调用。
            fileOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
