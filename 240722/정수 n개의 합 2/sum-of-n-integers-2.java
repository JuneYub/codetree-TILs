import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[] prefixSum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        prefixSum[1] = Integer.parseInt(st.nextToken());
        for(int i = 2; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i-1] +  num;
        }

        int max = 0;
        for(int i = 1; i <= n-k+1; i++) {
            
            
            max = Math.max(prefixSum[i+k-1] - prefixSum[i-1], max);
        }
        System.out.println(max);
    }
}