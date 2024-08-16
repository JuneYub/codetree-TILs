import java.io.*;
import java.util.*;

class Position implements Comparable<Position> {
    int start, end;

    Position(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Position p) {
        if(this.start == p.start) {
            return this.end - p.end;
        }
        return this.start - p.start;
    }

}

public class Main {

    public static int n;
    public static Position[] positions;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        positions = new Position[n];

        StringTokenizer st;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Position p = new Position(start, end);
            positions[i] = p;
        }

        Arrays.sort(positions);

        long left = 1;
        long right = 1_000_000_000;
        long ans = 1;
        while(left <= right) {
            
            long mid = (left + right)/2;

            if(isPossiable(mid)) {
                left = mid +1;
                ans = Math.max(ans, mid);
            } else {
                right = mid -1;
            }
        }

        System.out.print(ans);
        
    }

    public static boolean isPossiable(long dis) {
        
        int idx = 0;
        long cnt = 1;

        // 시작 구간의 모든 점을 돌면서 간격만큼 더해준다.
        for(int i = positions[0].start; i <= positions[0].end; i++) {
            if(isOk(i, dis)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOk(int start, long dis) {
        int curr = start;
        for(int i = 0; i < n-1; i++) {
            
            // 다음 구간의 시작점과 끝점 사이에 위치한다면 다음 구간으로 이동한다.
            if((curr + dis) >= positions[i+1].start && (curr + dis) <= positions[i+1].end) {
                //System.out.println("간격 : " + dis + " 현재 지점 : " + curr);
                curr += dis;
                continue;
            }

            else {
                // if(dis <= 10) {
                // System.out.println("시작점 : " + start + " 현재위치 : " + curr + " 간격 : " + dis + " 실패");
                // }
                return false;
            }
        }
        // if(dis <= 10) {
        // System.out.println("시작점 : " + start + " 간격 : " + dis + " 성공");
        // }
        return true;
    }
}