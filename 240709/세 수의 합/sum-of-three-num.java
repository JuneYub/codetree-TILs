import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX_N = 1000;

    // 변수 선언
    public static int n,k;
    public static int[] arr = new int[MAX_N];
    public static HashMap<Integer, Integer> freq = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(!freq.containsKey(arr[i])) {
                freq.put(arr[i], 1);
            } else {
                freq.put(arr[i], freq.get(arr[i]) + 1);
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            if(!freq.containsKey(arr[i])) {
                freq.put(arr[i], -1);
            } else {
               
                freq.put(arr[i], freq.get(arr[i]) - 1);
            }

            for(int j = 0; j < i; j++) {
                if(freq.containsKey(k-arr[i] - arr[j])) {
                    
                    ans += freq.get(k-arr[i] - arr[j]);
                }
                    
            }
        }

        System.out.print(ans);

    }
}