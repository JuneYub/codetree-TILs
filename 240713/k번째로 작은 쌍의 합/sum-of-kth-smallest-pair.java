import java.io.*;
import java.util.*;

class Tuple implements Comparable<Tuple> {
    int pairSum, idx1, idx2;

    public Tuple(int pairSum, int idx1, int idx2) {
        this.pairSum = pairSum;
        this.idx1 = idx1;
        this.idx2 = idx2;
    }

    public int compareTo(Tuple t) {
        if(this.pairSum != t.pairSum) {
            return this.pairSum - t.pairSum;
        }
        else if(this.idx1 != t.idx1) {
            return this.idx1 - t.idx1;
        }
        else {
            return this.idx2 - t.idx2;
        }
    }

}

public class Main {

    public static final int MAX_NUM = 100000;

    public static int n, m, k;

    public static int[] arr1 = new int[MAX_NUM];
    public static int[] arr2 = new int[MAX_NUM];
    public static PriorityQueue<Tuple> pq = new PriorityQueue<>();
    
    /**
    * 아이디어 : n수열의 모든 수를 m[0]과 일단 매칭 시켜 넣는다.
    * 그리고 각각의 n수열의 번호(두 수열의 합)가 우선순위 큐에 따라 오름차순으로 놓이면서
    * 진행되고 만약 최소값으로 선정되면 해당 m 수열 인덱스는 한칸 높여서 다시 넣어준다.
    * 이렇게 되면 완전 탐색이 아닌 그 순간 순간 최신을 갱신하면서 진행이 가능하다.
    *
    **/
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());        
        for(int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1, 0, n);
        Arrays.sort(arr2, 0, m);


        // n개의 원소에 대해 각각 m수열의 첫번째 원소를 대응시킨다. 왜냐하면 이게 제일 작을 확률이 높다.
        for(int i = 0; i < n; i++) {
            pq.add(new Tuple(arr1[i] + arr2[0], i, 0 ));
        }

        // 1번부터 k-1번까지 진행한다.
        for(int i = 0; i < k -1; i++) {
            Tuple bestT = pq.poll();
            int idx1 = bestT.idx1; // n수열의 인덱스
            int idx2 = bestT.idx2; // m수열의 인덱스

            // n수열의 idx와 더 매칭할 m수열의 번호가 있는지 체크하자
            idx2++; // m수열 한칸 이동
            if(idx2 < m) {
                pq.add(new Tuple(arr1[idx1] + arr2[idx2], idx1, idx2));
            }
        }

        // k번째 합을 출력
        System.out.print(pq.peek().pairSum);
    }
}