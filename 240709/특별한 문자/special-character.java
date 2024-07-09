import java.io.*;
import java.util.*;

public class Main {

    public static HashMap<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        char[] array = str.toCharArray();

        for(char c : array) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(char c : array) {
            if(map.get(c) == 1) {
                System.out.println(c);
                break;
            }
        }





    }
}