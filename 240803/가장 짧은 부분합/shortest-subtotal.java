import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sumV = 0;
        int j = 0;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {

            while(j < n && sumV + arr[j] < s ) {
                sumV += arr[j];
                // p2 한칸 이동
                j++;
                //System.out.println("합 : " + sumV + " j : " + j);
            }

            if(j < n && sumV + arr[j] >= s) {
                ans = Math.min(ans, j-i+1);
                //System.out.println("현재 구간 합 " + (sumV + arr[j]) + "ans갱신 " + ans);
            }

            sumV -= arr[i];
            //System.out.println(i+"빼고 합 : " + sumV);
        }
        if(ans == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(ans);
        }
        

    }
}