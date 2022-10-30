package FileTest;

import java.io.File;
import java.io.IOException;

public class test3 {
    public static void main(String[] args) {
        File file1 = new File("D:\\File\\yang\\study\\java\\test1.txt");
        File file2 = new File("D:\\File\\yang\\study\\java\\a\\b\\c");
        try {
            // 创建文件
            boolean flag1 = file1.createNewFile();
            System.out.println(flag1);
        }catch (IOException e){
            e.printStackTrace();
        }
        // 创建文件夹(只能创建一级文件夹)
        boolean flag2 = file1.mkdir();
        System.out.println(flag2);
        // 创建多级文件夹
        boolean flag3 = file2.mkdirs();
        System.out.println(flag3);


    }
}
