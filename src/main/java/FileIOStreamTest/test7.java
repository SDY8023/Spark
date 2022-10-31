package FileIOStreamTest;

import java.io.*;

/**
 * @ClassName test7
 * @Description
 * @Author SDY
 * @Date 2022/10/31 22:22
 **/
public class test7 {
    public static void main(String[] args) throws IOException {
        bufferedInputStream();
        bufferedOutputStream();

    }

    /**
     * 缓冲字节输入流
     */
    public static void bufferedInputStream() throws IOException {
        // 过程和InputStream一模一样的
        // 缓冲字节输入流流是需要基于一个字节输入流来进行实例化的
        // 在这里，BufferedInputStream构造方法中的InputStream对象，只是用来做当前的对象的实例化，在使用结束的时候，理论上来讲，是需要关闭的
        // 实际在使用中，使用结束后，只需要关闭BufferedInputStream即可。
        BufferedInputStream bufferedInputStream = null;
        try{
            bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\study\\NOTES\\JavaNotes\\08_IO流.pdf"));
            // 实例化一个字节数组
            byte[] bytes = new byte[1024];
            // 声明一个整型变量，来记录每次读取了多少个字节数据
            int length = 0;
            // 循环读取
            while ((length = bufferedInputStream.read(bytes)) != -1){
                // 将读取到的数据转成字符串输出到控制台
                String str = new String(bytes, 0, length);
                System.out.println(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedInputStream != null){
                bufferedInputStream.close();
            }
        }
    }

    /**
     * 缓冲字符输出流
     *
     */
    public static void bufferedOutputStream(){
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\study\\NOTES\\JavaNotes\\test2.txt"))){
            bufferedOutputStream.write("hello world".getBytes());
            bufferedOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
