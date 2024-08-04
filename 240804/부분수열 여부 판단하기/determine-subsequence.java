import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        int j = 0; // arr 의 포인터
        for(int i = 0; i < m; i++) {
            
            while(j + 1 <= n && arr[j] != arr2[i]) {
                j++;
            }

            if(arr[j] == arr2[i]) {
                if(i+1 >= m) System.out.print("Yes");
                continue;
            }

            if(j == n) {
                System.out.print("No");
                break;
            }

        }
    }
}