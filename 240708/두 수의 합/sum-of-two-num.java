import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        int result = 0;
        for(int i = 0; i < n; i++) {
            int num = arr[i];
            int num2 = k-num;

            if(map.containsKey(num) && map.containsKey(num2)) {

                if(num == num2) {
                    result += ((map.get(num)*(map.get(num)-1)) / 2);
                } else {
                    result += (map.get(num) * map.get(num2));
                }
                map.remove(num);
                map.remove(num2);
            }
        }
        System.out.println(result);
    }
}