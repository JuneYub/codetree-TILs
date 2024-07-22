import java.io.*;
import java.util.*;

public class Main {

    public static int[] prefixSum;
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

        for(int i = 0; i < b; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[num] = 1;
        }

        prefixSum = new int[n+1];
        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i-1] + arr[i];  
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= n - k + 1; i++) {
            ans = Math.min(ans, getSum(i, i + k -1));
        }
        System.out.print(ans);
    }

    public static int getSum(int s, int e) {
        return prefixSum[e] - prefixSum[s-1];
    }
}