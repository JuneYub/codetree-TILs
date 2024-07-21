import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] prefixSum = new int[n+1][n+1];


        for(int y = 1; y <= n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 1; x <= n; x++) {
                int num = Integer.parseInt(st.nextToken());
                prefixSum[y][x] = prefixSum[y][x-1] + prefixSum[y-1][x] - prefixSum[y-1][x-1] + num;
            }
        }

        int max = 0;
        for(int y = k; y <= n; y++) {
            for(int x = k; x <= n; x++) {
                int num = prefixSum[y][x] - prefixSum[y-k][x] - prefixSum[y][x-k] + prefixSum[y-k][x-k];
                max = Math.max(max, num);
            }
        }

        System.out.print(max);
    }
}