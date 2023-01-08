package HWJS;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

/**
 * @ClassName test26
 * @Description
 * @Author SDY
 * @Date 2023/1/8 20:15
 **/
public class test27 {
    public static void main(String[] args) throws ScriptException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.replace("[","(");
        input = input.replace("{","(");
        input = input.replace("}",")");
        input = input.replace("]",")");
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        System.out.println(scriptEngine.eval(input));
    }
}
