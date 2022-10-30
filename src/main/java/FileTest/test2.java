package FileTest;

import java.io.File;
import java.util.Date;

public class test2 {
    public static void main(String[] args) {
        File file = new File("D:\\File\\yang\\study\\java\\08_IO流.pdf");
        // 判断该文件路径上是否存在该文件
        System.out.println("exists = "+file.exists());
        // 判断路径上是否是一个文件
        System.out.println("isFile = " + file.isFile());
        // 判断File的路径是否是文件夹
        System.out.println("isDir = " + file.isDirectory());
        // 获取一个文件的大小
        System.out.println("length = " + file.length());
        // 判断文件(目录)是否是隐藏
        System.out.println("hidden = " + file.isHidden());
        // 判断文件(目录)的权限
        System.out.println("read = " + file.canRead());
        System.out.println("write = " + file.canWrite());
        System.out.println("execute = " + file.canExecute());
        // 获取文件名称
        System.out.println("name = " + file.getName());
        // 获取文件路径
        System.out.println("path = " + file.getPath());
        System.out.println("absolutePath = " + file.getAbsolutePath());
        // 获取父级文件夹的路径(字符串)
        System.out.println("parent = " + file.getParent());
        // 获取父级文件(File对象)
        System.out.println("parentFile = " + file.getParentFile());
        // 获取文件上次修改时间
        System.out.println("lastModified = " + file.lastModified());
        System.out.println(new Date(file.lastModified()));


    }
}
