import java.util.*;
import java.io.*;

class Position {
	int x,y;
	
	Position (int x, int y) {
		this.x = x;
		this.y = y;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		if(p.x == this.x && p.y == this.y) return true;
		return false;
	}
	
	
}

class Person implements Comparable<Person>{
	Position position;
	int moveDistance = 0;
	int between = 0;
	
	boolean isExit = false;
	
	Person(Position pos) {
		this.position = new Position(pos.x, pos.y);
	}
	
	// 출구와의 거리가 가까움 > y가 작아야함 > x가 더 작아야함
	public int compareTo(Person p) {
		if(this.between == p.between) {
			
			if(this.position.y == p.position.y) {
				
				return this.position.x - p.position.x;
			}
			
			return this.position.y - p.position.y;
		}
		return this.between - p.between;
	}
	
	public void updateBetween(int exitX , int exitY) {
		this.between = Math.max(Math.abs(exitY - this.position.y), Math.abs(exitX - this.position.x));	
	}
}

public class Main {
	
	static int n,m,k;
	static int[][] map;
	static List<Person> people = new ArrayList<>();
	static int exitX, exitY;
	// 좌우 상하
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static HashMap<Position, List<Person>> hashMap = new HashMap<>(); 
	
	static int livePeople;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 생존자livePeople
		livePeople = m;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			
			Position pos = new Position(x, y);
			
			Person person = new Person(pos);
			people.add(person);
			
			hashMap.putIfAbsent(pos, new ArrayList<Person>());
			hashMap.get(pos).add(person);
		}
		
		st = new StringTokenizer(br.readLine());
		exitY = Integer.parseInt(st.nextToken()) -1;
		exitX = Integer.parseInt(st.nextToken())-1;
		
		map[exitY][exitX] = -1;
		
		// 사람들의 출구까지의 거리 초기화
		for(Person person : people) {
			person.updateBetween(exitX, exitY);
		}
		
		int time = 0;
		while(time < k) {
			time++;
			
			// 참가자 이동 체크
			move();
			if(livePeople==0) break;
			// 가장 작은 정사각형 구하기
			rotationSquare();
			// 회전
			
			
		}
		
		printAns();
		
	}
	
	public static void printAns() {
		int ans = 0;
		for(Person person : people) {
			ans += person.moveDistance;
		}
		System.out.println(ans);
		System.out.println((exitY+1) + " " + (exitX+1));
	}
	
	public static void rotationSquare() {
		// 먼저 출구에서 가장 가까운 우선순위를 구해야한다.
		PriorityQueue<Person> pq = updateQueue();
		
		// 출구와 사람 한명은 무조건 엮어야함
		Person p = pq.peek();
		
		// 정사각형 한변의 길이
		int width = p.between+1;
		int topX = 0;
		int topY = 0;
		int bottomX = 0;
		int bottomY = 0;
		
		find:for(int y = 0; y < n; y++) {
			for(int x = 0; x < n; x++) {
				// 우측 하단 점이 범위 내에 있어야함
				bottomX = x + p.between;
				bottomY = y + p.between;
				if(isRange(bottomX, bottomY)) {
					
					
					// 출구가 범위내에 있는가?
					if (y <= exitY && exitY <= bottomY && x <= exitX && exitX <=bottomX) {
						// 현재 사람이 범위내에 있는가?
						for(Person person : people) {
							if(!person.isExit) {
								if (y <= person.position.y && person.position.y <= bottomY && x <= person.position.x && person.position.x <=bottomX) {
	
									topX = x;
									topY = y;
									break find;
									
								} 
							}
						}
					}
					
					

				}
			}
		}
		
		rotation(topX, topY, bottomX, bottomY, width);
		
	}
	
	private static void rotation(int topX, int topY, int bottomX, int bottomY, int width) {
		int[][] copyMap = new int[n][n];
		
		for(int y = 0; y < n; y++) {
			copyMap[y] = map[y].clone();
		}
		

		for(int y= 0; y < width; y++) {
			for(int x = 0; x < width; x++) {
				int orgY = topY +y;
				int orgX = topX + x;
				int copyY = topY + width-1-x;
				int copyX = topX + y;
				
				map[orgY][orgX] = copyMap[copyY][copyX];
				
				Position pos = new Position(copyX, copyY);
				if(hashMap.containsKey(pos)) {
					List<Person> list = hashMap.get(pos);
					
					for(Person person : list) {
						person.position.y = orgY;
						person.position.x = orgX;
					}
				}
				

			}
		}
			
		for(int y= 0; y < width; y++) {
			for(int x = 0; x < width; x++) {
				if(map[topY + y][topX + x] > 0) {
					map[topY + y][topX + x]--;
				}
				
				if(map[topY + y][topX + x] == -1) {
					exitY = topY + y;
					exitX = topX + x;
				}
			}
		}
		
//		System.out.println();
//		System.out.println(topY + " " + topX +" 에서" + width + " 만큼 회전");
//		System.out.println();
//		for(int y= 0; y < n; y++) {
//			for(int x = 0; x < n; x++) {
//				System.out.print(map[y][x] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		people.clear();
		
		for(Map.Entry<Position, List<Person>> entry : hashMap.entrySet()) {
			List<Person> list = entry.getValue();
			
			for(Person p : list) {
				people.add(p);
			}
		}
	}

	public static void move() {
		
		hashMap.clear();
		
		for(Person person : people) {
			if(!person.isExit) {
				
				int min = checkBetween(person.position.x, person.position.y);
				int moveX = person.position.x;
				int moveY = person.position.y;
				// 좌우 상하 순으로 체크
				for(int i = 0; i < 4; i++) {
					int nx = person.position.x + dx[i];
					int ny = person.position.y + dy[i];
					
					// 지도 나가면 넘어감
					if(!isRange(nx, ny)) continue;
					// 벽이어도 넘어감
					if(map[ny][nx] > 0) continue;
					
					if(checkBetween(nx, ny) < min) {
						// 좌우 상하 순으로 이동할 위치를 업데이트하면 마지막에 이동할 부분이 세팅됨
						moveX = nx;
						moveY = ny;
					}
				}
				
				// 이동하지 못한경우라면 다음 사람으로 진행
				if(moveX == person.position.x && moveY == person.position.y) {
					
					Position pos = new Position(person.position.x, person.position.y);
					person.updateBetween(exitX, exitY);
					hashMap.putIfAbsent(new Position(person.position.x, person.position.y), new ArrayList<Person>());
					hashMap.get(pos).add(person);
					continue;
				}
				
				//System.out.println(person.position.y + " " + person.position.x + " 에서");
				// 사람이동
				person.position.x = moveX;
				person.position.y = moveY;
				
				//System.out.println(person.position.y + " " + person.position.x + " 으로 이동");
//				if(person.position.x == exitX && person.position.y == exitY) {
//					System.out.println("도착");
//				}
				
				// 출구까지의 거리 업데이트
				person.updateBetween(exitX, exitY);
				
				// 이동 거리 업데이트
				person.moveDistance++;
				
				// 이동했는데 출구라면? 도착한걸로 수정
				if(person.position.x == exitX && person.position.y == exitY) {
					person.isExit = true;
					livePeople--;
					// 이렇게 하면 출구에 도착한 위치가 해당 인물의 엔드포인트로 된다.
				}
			}
			
			
			Position pos = new Position(person.position.x, person.position.y);
			hashMap.putIfAbsent(new Position(person.position.x, person.position.y), new ArrayList<Person>());
			hashMap.get(pos).add(person);
		}
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= n) return false;
		return true;
	}
	
	public static int checkBetween(int nx, int ny) {
		return (Math.abs(exitX - nx) + Math.abs(exitY - ny));
	}
	
	public static PriorityQueue<Person> updateQueue( ) {
		PriorityQueue<Person> pq = new PriorityQueue<>();
		for(Person person : people) {
			if(!person.isExit) {
				pq.add(person);
			}
		}
		
		return pq;
	}
}