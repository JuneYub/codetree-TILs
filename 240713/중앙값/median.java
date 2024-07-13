import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int testCase = 0; testCase < t; testCase++) {

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            // 최대힙과 최소힙을 활용해 중앙값을 구하기
            // 최대힙에는 항상 홀수번째 수가 삽입된다. 
            // 만약 최대힙의 값이 최소힙보다 크다면 두 top을 바꿔준다.

            
            for(int i = 1; i <= m; i++) {
                int num = Integer.parseInt(st.nextToken());

                if(i % 2 != 0) {
                    maxHeap.add(-num);
                } else {
                    minHeap.add(num);
                }

                if(!maxHeap.isEmpty() && !minHeap.isEmpty() && -maxHeap.peek() > minHeap.peek()) {
                    int maxTmp = -maxHeap.poll();
                    int minTmp = minHeap.poll();

                    maxHeap.add(-minTmp);
                    minHeap.add(maxTmp);
                }

                //System.out.println(maxHeap.peek() + " " + minHeap.peek());

                if(i % 2 != 0) {
                    sb.append(-maxHeap.peek() +" ");
                }

            }
            //System.out.println();
            sb.append("\n");
        }

        System.out.print(sb);
    }
}