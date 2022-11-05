package FileIOStreamTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * @ClassName test10
 * @Description
 * @Author SDY
 * @Date 2022/11/5 20:15
 **/
public class test10 {
    public static void main(String[] args) {
        try(BufferedInputStream bis = new BufferedInputStream(System.in)){
            byte[] bytes = new byte[128];
            int length = 0;
            while ((length = bis.read(bytes)) != -1){
                final String str = new String(bytes, 0, length);
                System.out.println(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
