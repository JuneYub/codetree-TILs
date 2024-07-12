import java.util.*;
import java.io.*;


/**
 * 사람 목록과 인접한 두 사람이 만나는 사건을 TreeSet으로 관리하자.
 * TreeSet으로 사람 목록을 관리하면 "순서"대로 관리되기 때문에
 * 이전 사람이 앞 사람과 만나는 순간을 확인할 수 있다.
 */

/**
 * 필요한 목록 사람 클래스
 * 현재위치, 속도
 */

class Person implements Comparable<Person> {
    int x, v;

    public Person(int x, int v) {
        this.x = x;
        this.v = v;
    }

    // 위치를 기준으로 오름차순 한다.
    public int compareTo(Person p){
        return this.x - p.x;
    }
}

/**
 * 이벤트 클래스
 * 시간, 위치, 속도
 */
class Event implements Comparable<Event> {
    double currT;
    int x, v;

    public Event(double currT, int x, int v) {
        this.currT = currT;
        this.x = x;
        this.v = v;
    }

    public int compareTo(Event e){
        double diff = this.currT - e.currT;

        // 오름차순 정렬
        if(diff < 0) return -1;

        // 값이 동일하면 x기준 오름차순 정렬
        else if(diff == 0) return this.x - e.x;

        else return 1;

    }
}


public class Main {
    public static final int MAX_N = 100000;
    public static int n, t;
    public static TreeSet<Person> peopleX = new TreeSet<>();
    public static TreeSet<Event> eventT = new TreeSet<>();

    public static int[] x = new int[MAX_N];
    public static int[] v = new int[MAX_N];

    /**
     * x1,v1 사람이 x2,v2 와 마주치는 데 걸리는 시간 정보를 얻어 사건 추가
     */
    public static void addEvent(int x1, int v1, int x2, int v2) {
        // v2가 더 빠르니깐 절대 따라잡을 수 없다.
        if(v1 <= v2) return;

        // v2 > v1 의 속도인 경우 해당됩니다.
        eventT.add(new Event(1.0 * (x2 - x1) / (v1 - v2), x1 , v1));
    }

    /**
     * 뒤에 있는 사람이 앞에 있는 사람과 마주치는 데 걸리는 시간을 구해 사건 제거
     */
    public static void removeEvent(int x1, int v1, int x2, int v2) {
        if(v1 <= v2) return;

        eventT.remove(new Event(1.0 * (x2 - x1) / (v1 - v2), x1, v1));
    }



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            peopleX.add(new Person(x[i], v[i]));
        }

        /**
         * 인접한 사람끼리 만나게 되는 사건을 관리하자
         * 현재 x 위치에서 v 속도로 이동하는 사람과
         * 바로 앞에 있는 사람이
         * 마주치는 데 걸리는 시간 t를 입력한다.
         */
        for(int i = 0; i < n-1; i++) {
            addEvent(x[i], v[i], x[i+1], v[i+1]);
        }

        while(!eventT.isEmpty()) {
            Event e = eventT.first();
            double currT = e.currT;
            int x = e.x, v =  e.v;

            // 주어진 시간을 초과했다면 종료
            if(currT > t) {
                break;
            }

            // 앞 사람과 만나게 되었다면 해당 사람을 목록에서 없앤다.
            peopleX.remove(new Person(x, v));
            eventT.remove(new Event(currT, x, v));

            // 추월하게 된 사람의 다음 위치에 있는 사람을 구하고
            // 그 사람의 이전 사람의 위치를 구해서 새로운 사건을 추가한다.
            Person nextPerson = peopleX.higher(new Person(x, v));
            int nextX = nextPerson.x;
            int nextV = nextPerson.v;

            // 바로 뒤에 사람이 있는지 확인하자.
            if(peopleX.lower(nextPerson) != null) {
                Person pp = peopleX.lower(nextPerson);
                int px = pp.x;
                int pv = pp.v;

                // 이전 사람과 연결된 사건은 제거한다.
                removeEvent(px, pv, x, v);
                // 새로운 사건을 추가한다.
                addEvent(px, pv, nextX, nextV);
            }
        }

        System.out.print(peopleX.size());
    }
}