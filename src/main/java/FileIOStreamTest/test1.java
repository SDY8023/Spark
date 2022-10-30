package FileIOStreamTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class test1 {
    public static void main(String[] args) {
        new test1().ioStream();
    }
    public void ioStream(){
        // 在外面声明变量
        InputStream inputStream = null;
        try {
            // 实例化一个FileInputStream对象，向上转型为InputStream类型类型。
            // 这个实例化如果完成，将会建立程序与文件之间的连接。
            // 建立好之后，数据就可以从文件中流动到程序中。
            // 注意: 数据流动到程序中，并不意味着文件中没有数据了！
            // 这个过程中，会出现 FileNotFoundException 的异常，原因: 路径写错了，这个路径上没有文件
            inputStream = new FileInputStream("file\\day25\\source");
            // 数据的读取操作
            // 在数据读取的过程中，也会出现 IOException 异常。一旦出现异常，后序的代码都不执行了，直接执行catch语句了
            // 流的关闭，不能放到try里面。需要放到finally中。
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 流在使用结束之后，一定要进行关闭。
            if(inputStream != null){
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
