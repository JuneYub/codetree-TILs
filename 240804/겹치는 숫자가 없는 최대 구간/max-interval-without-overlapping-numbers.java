import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] countArr = new int[100_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i+1] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int j = 0;

        for(int i = 1; i <= n; i++) {
            
            while(j + 1 <= n && countArr[arr[j+1]] < 1) {
                //System.out.print(j+1);
                countArr[arr[j+1]]++;
                j++;
            }
            
            ans = Math.max(ans, j-i+1);
            //System.out.println(i+" i는 옮기기");
            countArr[arr[i]]--;
        }

        System.out.print(ans);
    }
}