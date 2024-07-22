import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1~n
        int n = Integer.parseInt(st.nextToken());
        // k개의 숫자들이 연속해야 하나의 세트가 됨, 적어도 1세트는 이루자
        int k = Integer.parseInt(st.nextToken());
        // b개의 숫자들이 빠져있음
        int b = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);
        arr[0] = 0;

        for(int i = 0; i < b; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[num] = 0;
        }

        int[] prefixSum = new int[n+1];
        for(int i = 1; i <= n; i++) {
            if(arr[i] == 0) {
                prefixSum[i] = 0;
                continue;
            }
            prefixSum[i] = prefixSum[i-1] + 1;  
        }

        int max = 0;

        for(int i = 0; i <= n; i++) {
            if(prefixSum[i] == 0) continue;
            for(int j = i+1; j <= n; j++) {
                if(prefixSum[j] == 0) continue;
                max = Math.max(prefixSum[i] + prefixSum[j], max);

            }
        }

        System.out.print(k-max);


    }
}