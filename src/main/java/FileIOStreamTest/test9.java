package FileIOStreamTest;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @ClassName test9
 * @Description
 * @Author SDY
 * @Date 2022/11/5 20:11
 **/
public class test9 {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new File("D:\\study\\NOTES\\JavaNotes\\test1.txt"))){
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
