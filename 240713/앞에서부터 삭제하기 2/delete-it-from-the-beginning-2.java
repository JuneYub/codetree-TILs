import java.io.*;
import java.util.*;

public class Main {

    public static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static int[] arr;
    public static int[] minArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        minArr= new int[n];

        double sum = 0.0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int k = 0; k < n; k++) {
            int num = Integer.parseInt(st.nextToken());
            arr[k] = num;
            sum += num;
        }

        // n개의 수를 뒤에서부터 순회하면서 그때그때의 최솟값을 구한다.
        pq.add(arr[n-1]);
        pq.add(arr[n-2]);
        for(int k = n-3; k >= 0; k--) {
            // 현재 지점의 최소값이란 우선순위 큐에다가 현재 수를 제외한 뒤에 수중에서 최솟값을 의미한다.
            minArr[k] = pq.peek();
            pq.add(arr[k]);
            // 1 1 2 2 0 <= 이 말은 해당 k까지 제거하고 남은 수 중에서 최소 값을 의미한다.
            
        }

        // 누적합에서 해당 수를 제거하고 평균을 구한다.
        double result = Double.MIN_VALUE;

        for(int k = 0; k < n-2; k++) {
            sum -= arr[k]; // 전체에서 k번째를 제거한다.
            // 누적합에서 현재 부분을 빼고 k개를 제거했을 때 가장 작은 값을 뺀 후 평균을 구한다.
            double tmp = (sum - minArr[k]) / (n - (k+1) - 1);
            result = Math.max(result, tmp);
        }

        System.out.printf("%.2f", result);
        
    }
}