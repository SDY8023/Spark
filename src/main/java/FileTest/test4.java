package FileTest;

import java.io.File;

public class test4 {
    public static void main(String[] args) {
        File file1 = new File("D:\\File\\yang\\study\\java");
        // 列举一个目录下的所有的子文件的名称
        String[] fileNameList = file1.list();
        for (String fileName : fileNameList) {
            System.out.println(fileName);
        }
        System.out.println("====================");
        // 列举一个目录下所有的子文件名字，带有过滤信息
        String[] list1 = file1.list((f, name) -> {
            return name.startsWith(".");
        });
        for (String s : list1) {
            System.out.println(s);
        }
        System.out.println("====================");
        // 列举一个目录下的所有子文件(以File对象的方式)
        File[] files = file1.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        System.out.println("====================");
        // 列举一个目录下指定条件的文件
        File[] files1 = file1.listFiles(File::isHidden);
        for (File file : files1) {
            System.out.println(file);
        }
    }
}

