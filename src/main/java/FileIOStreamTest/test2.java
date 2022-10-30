package FileIOStreamTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) {
        /**
         * try结构的特殊语法: try ()
         * 将 AutoClosable 接口的实现类对象的实例化放到小括号中。
         * 此时，在离开了try结构的时候，会自动的对这个类进行close方法的调用
         */
        try(FileInputStream fileInputStream = new FileInputStream("")){
            // 数据的读取操作
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(new File("file\\day25\\source").delete());
    }
}
