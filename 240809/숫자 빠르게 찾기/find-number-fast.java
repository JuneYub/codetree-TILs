import java.io.*;
import java.util.*;

public class Main {

    public static int[] arr;
    static int n;
    static int m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());

            System.out.println(findNum(num));
        }
    
    }

    public static int findNum(int target) {
        
        int left = 0;
        int right = n-1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(arr[mid] == target) return mid + 1;

            if(arr[mid] < target) {
                left = mid + 1;
            }

            else if(arr[mid] > target) {
                right = mid -1;
            }
        }

        return -1;
    }
}