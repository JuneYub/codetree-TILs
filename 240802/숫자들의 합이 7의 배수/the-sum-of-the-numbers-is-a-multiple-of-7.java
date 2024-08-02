import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 7 3 5 1 6 2 14 10

        // 0 3 5 1 6 2 0 3
        // 0 3 8 9 15 17 17 20
        // 
        // 48
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        long[] prefixSum = new long[n];
        
        prefixSum[0] = Integer.parseInt(br.readLine()); 
        for(int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            prefixSum[i] = prefixSum[i-1] + arr[i];
            
        }

        long[] divine = new long[n];
        for(int i = 0; i < n; i++) {
            divine[i] = (prefixSum[i] % 7);
            //System.out.print(divine[i] + " ");
        }
        //System.out.println();

        long ans = 0;
        HashMap<Long, Long> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            long num = divine[i];
            if(map.get(num) == null) {
                map.put(num, (long)i);
            } else {
                ans = Math.max(ans, (long)i - map.get(num));
            }
        }
        
        System.out.println(ans);
    
    }
}