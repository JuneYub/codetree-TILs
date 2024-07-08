import java.io.*;
import java.util.*;

public class Main {
    public static final int MAX_N = 5000;

    // 변수 선언
    public static int n;
    public static int[] A = new int[MAX_N];
    public static int[] B = new int[MAX_N];
    public static int[] C = new int[MAX_N];
    public static int[] D = new int[MAX_N];
    public static HashMap<Integer, Integer> freq = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) B[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) C[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) D[i] = Integer.parseInt(st.nextToken());

        long ans = 0;

        // A 수열에서 1개, B수열에서 1개를 골라 더해서 map에 추가
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int key = A[i] + B[j];
                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }
        }

        // C,D 순회하여 쌍을 생성, map을 활용해서 총합이 0이 되는 쌍의 개수 파악
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int diff = -C[i] -D[j];
                if(freq.getOrDefault(diff, 0) > 0) {
                    ans += freq.get(diff);
                }
            }
        }
        System.out.println(ans);

    }
}