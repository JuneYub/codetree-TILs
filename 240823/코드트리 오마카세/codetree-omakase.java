import java.util.*;
import java.io.*;

public class Main {
	
	static class Queries {
		int cmd, t, x, n;
		String name;
		
		Queries(int cmd, int t, int x, String name, int n) {
			this.cmd = cmd;
			this.t = t;
			this.x = x;
			this.name = name;
			this.n = n;
		}
	}

	static int l, q;
	static List<Queries> queue = new ArrayList<>();
	static Set<String> people = new HashSet<>();
	static Map<String, Integer> entry = new HashMap<>();
	static Map<String, Integer> exit = new HashMap<>();
	static Map<String, List<Queries>> sushi = new HashMap<>();
	static Map<String, Integer> position = new HashMap<>();
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	l = Integer.parseInt(st.nextToken());
    	q = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < q; i++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = -1, t = -1, x = -1, n = -1;
    		String name = "";
    		
    		cmd = Integer.parseInt(st.nextToken());
    		
    		switch(cmd) {
    			
    			case 100:
    				t = Integer.parseInt(st.nextToken());
    				x = Integer.parseInt(st.nextToken());
    				name = st.nextToken();
    				Queries query = new Queries(cmd, t, x, name, n);
    				queue.add(query);
    				sushi.computeIfAbsent(name, k -> new ArrayList<>()).add(query);
    				
    			break;
    				
    			case 200:
    				t = Integer.parseInt(st.nextToken());
    				x = Integer.parseInt(st.nextToken());
    				name = st.nextToken();
    				n = Integer.parseInt(st.nextToken());
    				
    				queue.add(new Queries(cmd, t, x, name, n));
    				people.add(name);
    				entry.put(name, t);
    				position.put(name, x);
    				
    			break;
    			
    			case 300:
    				t = Integer.parseInt(st.nextToken());
    				queue.add(new Queries(cmd, t, x, name, n));
    			break;
    		}
    	}
    	
    	// 자신의 초밥을 언제 먹었는지 계산해서 쿼리에 넣는다. 111
    	for(String name : people) {
    		exit.put(name, 0);
    		
    		int removeTime = 0;
    		
    		for(Queries query : sushi.get(name)) {
    			
    			// 스시가 사람보다 먼저 등장했다면
    			if(query.t < entry.get(name)) {
    				// 스시의 당시 위치와 사람의 위치 간격을 계산한 후에 몇초가 지나면 해당 위치에 가게 되는지 구한다.
    				int orgX = query.x;
    				int peopleX = position.get(name);
    				
    				orgX = (orgX + (entry.get(name) - query.t)) % l;
    				int additionalTime = (peopleX - orgX + l)%l;
    				removeTime = additionalTime + entry.get(name);
    			}
    			
    			// 사람이 스시보다 먼저 등장했다면
    			else {
    				int additionalTime = (position.get(name) - query.x + l)%l;
    				removeTime = additionalTime + query.t;
    			}
    			
    			exit.put(name, Math.max(exit.get(name), removeTime));
    			// 초밥이 사라지는 쿼리
        		queue.add(new Queries(111, removeTime, -1, name, -1));
    			
    		}
    		
    		
    	}
    	
    	// 사람이 떠났다는 쿼리
    	for(String name : people) {
    		queue.add(new Queries(222, exit.get(name), -1, name, -1));
    	}
    	
    	// 전체 쿼리를 시간순으로, 쿼리의 커맨드 순으로 정렬
    	Collections.sort(queue, new Comparator<Queries>() {
    		
    		public int compare(Queries q1, Queries q2) {
    			if(q1.t == q2.t) {
    				return q1.cmd -q2.cmd;
    			}
    			return q1.t - q2.t;
    		}
    	});
    	
    	int peopleNum = 0;
    	int sushiNum = 0;
    	
    	for(int i = 0; i < queue.size(); i++) {
    		
    		int cmd = queue.get(i).cmd;
    		switch(cmd) {
    		case 100:
    			sushiNum++;
    			break;
    		case 111:
    			sushiNum--;
    			break;
    		case 200:
    			peopleNum++;
    			break;
    		case 222:
    			peopleNum--;
    			break;
    		case 300:
    			System.out.println(peopleNum + " " + sushiNum);
    			break;
    			
    		}
    	}

        
        
        
    }
}