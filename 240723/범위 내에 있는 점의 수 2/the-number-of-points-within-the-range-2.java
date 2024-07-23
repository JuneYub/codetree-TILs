import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int maxIdx = 1_000_001;
        
        int[] arr = new int[maxIdx];
        int[] prefixSum = new int[maxIdx];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int idx = Integer.parseInt(st.nextToken());
            arr[idx] = 1;
        }

        for(int i = 1; i <= maxIdx-1; i++) {
            prefixSum[i] = prefixSum[i-1] + (arr[i] == 1 ? 1 : 0 );
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int cnt = prefixSum[e] - prefixSum[s-1];
            sb.append(cnt+"\n");
        }
        System.out.print(sb);

    }
}