import java.util.*;
import java.io.*;

class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static int n,q;
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        
        // 오름차순으로 정렬해서 해시맵에 다시 세팅해준다.
        Map<Integer, Integer> map = new HashMap<>();

        int cnt = 1;
        for(int num : arr) {
            map.put(num, cnt++);
        }

        // 펜윅트리 초기화
        int[] FenwickTree = new int[n+1];
        for(int i = 1; i <= n; i++) {
            FenwickTree[i] = FenwickTree[i-1] + 1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int transIdxA = map.get(s);
            int transIdxB = map.get(e);


            sb.append(FenwickTree[transIdxB] - FenwickTree[transIdxA-1] +"\n" );
        }

        System.out.print(sb);
    }
}