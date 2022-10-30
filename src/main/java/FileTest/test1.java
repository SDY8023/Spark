package FileTest;

import java.io.File;

public class test1 {
    public static void main(String[] args) {
        // File(String FileName)
        // 若文件不存在，不影响File对象的实例化
        File file1 = new File("D:\\File\\yang\\study\\java\\08_IO流11.pdf");
        System.out.println(file1.exists());
        //2. File(String parent,String child)
        // 此构造方法将parent,child结合在一起
        File file2 = new File("D:\\File\\yang\\study\\java", "08_IO流11.pdf");
        System.out.println(file2);
        System.out.println(file2.exists());
        //3.File(File parent,String child)
        // 此构造方法中，将parent的文件与child拼接
        File file3 = new File(new File("D:\\File\\yang\\study\\java"), "08_IO流11.pdf");
        System.out.println(file3);
        System.out.println(file3.exists());
    }
}
