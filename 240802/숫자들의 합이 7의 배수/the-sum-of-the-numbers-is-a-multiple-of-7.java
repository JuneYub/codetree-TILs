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
        int[] prefixSum = new int[n];
        
        prefixSum[0] = Integer.parseInt(br.readLine()); 
        for(int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            prefixSum[i] = prefixSum[i-1] + arr[i];
            
        }
        
        int maxLen = 0;
        for(int i = 0; i < n; i++) {
            for(int j = n-1; j > i; j--) {
                int num = (prefixSum[j] - prefixSum[i])%7;
                
                if(num == 0) {
                    //System.out.println(j + " " + i);
                    maxLen = Math.max(maxLen, j-i);
                } 
            }
        }
        System.out.println(maxLen);
    
    }
}