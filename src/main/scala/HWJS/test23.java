package HWJS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName test23
 * @Description
 * @Author SDY
 * @Date 2023/1/1 13:50
 **/
public class test23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s1 = scanner.nextLine();
            String[] s2 = s1.split(" ");
            int n = Integer.parseInt(s2[0]);
            int m = Integer.parseInt(s2[1]);
            int[][] map = new int[n][m];
            for(int i = 0;i < n;i++){
                String s3 = scanner.nextLine();
                String[] s4 = s3.split(" ");
                for(int j = 0;j < m;j++){
                    map[i][j] = Integer.parseInt(s4[j]);
                }
            }
            List<Pos> path = new ArrayList<>();
            dfs(map,0,0,path);
            for(Pos s:path){
                System.out.println("("+s.x+","+s.y+")");
            }

        }
    }
    public static boolean dfs(int[][] map,int x,int y,List<Pos> path){
        path.add(new Pos(x,y));
        map[x][y] = 1;
        if(x == map.length - 1 && y == map[0].length - 1){
            return true;
        }
        // 向下
        if(x+1 < map.length && map[x+1][y] == 0){
            if(dfs(map,x+1,y,path)){
                return true;
            }
        }
        // 向上
        if(x-1 >= 0 && map[x-1][y] == 0){
            if(dfs(map,x-1,y,path)){
                return true;
            }
        }
        // 向右
        if(y+1 < map[0].length && map[x][y+1] == 0){
            if(dfs(map,x,y+1,path)){
                return true;
            }
        }
        // 向左
        if(y-1 >= 0 && map[x][y-1] == 0){
            if(dfs(map,x,y-1,path)){
                return true;
            }
        }
        path.remove(path.size()-1);
        map[x][y] = 0;
        return false;
    }

    public static class Pos{
        int x;
        int y;
        public Pos(int x,int y){
            this.x = x;
            this.y = y;
        }

    }
}

