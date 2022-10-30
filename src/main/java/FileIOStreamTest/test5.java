package FileIOStreamTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test5 {
    public static void main(String[] args) {
        boolean flag = copy("D:\\File\\yang\\study\\java\\test1.txt", "D:\\File\\yang\\study\\java\\test2.txt");
        System.out.println(flag);

    }
    private static boolean copy(String srcPath,String dstPath){
        // 判断目标路径是否存在
        File dstFile = new File(dstPath);
        if(dstFile.exists()){
            return false;
        }
        // 实现文件的拷贝
        try(FileInputStream fileInputStream = new FileInputStream(srcPath);
            FileOutputStream fileOutputStream = new FileOutputStream(dstPath)){
            byte[] bytes = new byte[1024];
            // 声明一个整型变量用来记录每次读取到了多少个字节的数据
            int length = 0;
            // 读取数据
            while ((length = fileInputStream.read(bytes)) != -1){
                // 将读取的数据写入到输出流中
                fileOutputStream.write(bytes,0,length);
            }
            // 冲刷缓冲区
            fileOutputStream.flush();
            return true;
        }catch (IOException e){
            System.out.println(e);
            return false;
        }


    }
}
