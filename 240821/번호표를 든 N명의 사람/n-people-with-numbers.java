import java.io.*;
import java.util.*;

class Person implements Comparable<Person> {
    int startTime;
    int needTime;
    int finishTime;

    Person(int startTime, int needTime) {
        this.startTime = startTime;
        this.needTime = needTime;
        this.finishTime= startTime + needTime;
    }

    public int compareTo(Person p) {
        return this.finishTime - p.finishTime;
    }
}

public class Main {

    static int n;
    static int tmax;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        tmax = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = 1;
        int right = 10_000;
        int ans = 10_000;

        while(left <= right) {
            int mid = (left + right) / 2;
            int length = arr.length;
            if(mid > length) {
                right = mid - 1;
                continue;
            }

            // k인원으로 가능하다면
            if(isPossiable(mid)) {
                right = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                // k인원으로 불가능하다면, 
                left = mid + 1;
            }
        }

        System.out.print(ans);
    }

    // k 인원만큼 무대에 사람을 세운다.
    public static boolean isPossiable(int k) {

        PriorityQueue<Person> pq = new PriorityQueue<>();

        int idx = 0; // 무대에 올라간 마지막 인원의 순서
        int time = 0; // 현재 시간
        while(idx < n) {

            // 우선순위 큐가 비어있지 않다면
            if(!pq.isEmpty()) {
                
                int finishTime = pq.peek().finishTime;
                // 끝나는 시간이 같은 사람들은 무대에서 내려간다.
                while(finishTime == pq.peek().finishTime) {
                    //System.out.println(k+"에서 현재시간은 " + time + "빼는 값은 : " + pq.peek().finishTime);
                    pq.poll();
                }

                // 현재시간 갱신
                time = finishTime;
                //System.out.println("현재 시간 " + time);
                
                while(pq.size() < k && idx < n) {
                    // 우선순위 큐(무대)가 비어있다면 사람을 추가한다.
                    //System.out.println(k+"에서 현재시간은 " + time + "추가할 값은 : " + arr[idx]);
                    pq.add(new Person(time, arr[idx++]));
                }

            } else {

                while(pq.size() < k && idx < n) {
                    // 우선순위 큐(무대)가 비어있다면 사람을 추가한다.
                    //System.out.println(k+"에서 현재시간은 " + time + "추가할 값은 : " + arr[idx]);
                    pq.add(new Person(time, arr[idx++]));
                }
            }
        }

        //System.out.println(k+"에서 모든 탐색이 끝나고 pq 사이즈 :" + pq.size() + " "); 

        int finalLstTime = 0;
        // idx >= n 경우에 반복문은 종료되고 무대에서 사람들이 모두 내려가는 시간은 제일 마지막 사람이 가지고 있음
        while(!pq.isEmpty()) {
            //System.out.println(k+"에서" + pq.peek().needTime + " " + pq.peek().finishTime);
            finalLstTime = pq.poll().finishTime;
            
        }

        finalLstTime = Math.max(time, finalLstTime);

        //System.out.println(k + "에서 최종 마지막 시간은 " + finalLstTime); 

        if(finalLstTime > tmax) return false;
        return true;
    }
}