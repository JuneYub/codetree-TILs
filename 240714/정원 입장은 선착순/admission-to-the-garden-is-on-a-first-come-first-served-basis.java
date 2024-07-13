import java.io.*;
import java.util.*;


class Person implements Comparable<Person> {
    int a,t, no;
    Person(int no, int a, int t) {
        this.no = no;
        this.a = a;
        this.t = t;
    }

    public int compareTo(Person p) {
        if(this.a != p.a) {
            return this.a - p.a;
        } else {
            return this.no - p.no;
        }

    }
}

public class Main {

    public static PriorityQueue<Person> people = new PriorityQueue<>();
    public static PriorityQueue<Person> waiting = new PriorityQueue<>(new Comparator<Person>() {
        public int compare(Person p1, Person p2) {
            return p1.no - p2.no;
        }
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            people.add(new Person(i, a, t ));
        }

        int result = 0;
        int time = 0;
        while(!people.isEmpty() || !waiting.isEmpty()) {
            
            // 웨이팅이 없는 경우 바로 사람을 뽑아서 시작시간과 대기시간을 더한다.
            if(waiting.isEmpty()) {
                Person tour = people.poll();
                int nextTime = tour.a + tour.t;
                
                // 다음 시간까지 이전까지의 사람들을 모두 웨이팅 리스트에 넣는다.
                while(!people.isEmpty() && people.peek().a <= nextTime) {
                    waiting.add(people.poll());
                }

                // 현재 시간은 공원투어가 끝난 시간이다.
                time = nextTime;
            }

            else {
                Person tour = waiting.poll();
                result = Math.max(result, time - tour.a);
                // 관람이 시작된 시간 + 구경 시간은 다음 시간이 된다.
                int nextTime = time + tour.t;

                // 다음 시간까지 이전까지의 사람들을 모두 웨이팅 리스트에 넣는다.
                while(!people.isEmpty() && people.peek().a <= nextTime) {
                    waiting.add(people.poll());
                }

                // 현재 시간은 공원투어가 끝난 시간이다.
                time = nextTime;    
            }
        }

        System.out.println(result);


    }
}