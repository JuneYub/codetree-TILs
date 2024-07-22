import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] prefixSum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        prefixSum[1] = Integer.parseInt(st.nextToken());
        for(int i = 2; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            prefixSum[i] =  num + prefixSum[i-1];
        }

        int cnt = 0;
        for(int i = 0; i <= n; i++) {
            for(int j = i+1; j <=n; j++) {
                int num = prefixSum[j] - prefixSum[i];
                if(num == k) {
                    cnt++;
                }
            }
        }

        System.out.print(cnt);
    }
}