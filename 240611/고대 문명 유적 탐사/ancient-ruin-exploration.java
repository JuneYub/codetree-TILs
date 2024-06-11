import java.util.*;
import java.io.*;

class Position {
	int x, y;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Point implements Comparable<Point> {
	int x,y,point,d;
	List<Position> list = new ArrayList<>();
	
	Point(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
	
	public int compareTo(Point p) {
		// 얻은 유물로 내림차순 정리
		if(p.point == this.point) {
			
			// 각도로 오름차순 정리
			if(p.d == this.d) {
				
				// 열로 오름차순 정리
				if(p.x == this.x) {
					
					// 열까지 같으면 행 기준 오름차순 정리
					return this.y - p.y;
					
				} else {
					return this.x - p.x;
				}
				
			} else {
				return this.d - p.d;
			}
			
		} else {
			return p.point - this.point;
		}
	}
	
	
}

public class Main {
	
	static int[][] map = new int[5][5];
	static int[] dummy;
	
	static int[] dx = {-1,0,1,1,1,0,-1,-1};
	static int[] dy = {-1,-1,-1,0,1,1,1,0};
	
	static int[] dirX = {0, 1, 0, -1};
	static int[] dirY = {-1, 0, 1, 0};
	
	static PriorityQueue<Point> pq;
	
	static int pickIdx = 0;
	static int answer = 0;
	static int k,m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		k = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dummy = new int[m];
		
		for(int y = 0; y < 5; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < 5; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			dummy[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		
		//시작
		for(int i = 0; i < k; i++) {
			answer = 0;
			game();
			if(answer != 0) {
				sb.append(answer + " ");
			}
		}
		System.out.println(sb.toString());
		
	}
	
	// k만큼 유물 찾기 시작
	public static void game() {
		
		
		// 우선순위큐를 활용해서 지점의 각도별 얻어지는 유물의 개수를 비교해서
		// 가장 높은 우선순위의 지점을 가져올 수 있도록 한다.
		pq = new PriorityQueue<>();
		
		// 5x5에서 3x3만큼 큐브를 돈다.
		for(int y = 1; y <= 3; y++) {
			for(int x = 1; x <= 3; x++) {
				
				for(int i = 1; i <= 3; i++) {
					int[][] copyMap = rotation90(x, y, i);
					bfs(copyMap, x, y, i);
				}
				
			}
		}
		
		// 가져온 지점의 획득 유물이 1이상인 경우에
		if(pq.peek().point >= 1) {
			
			rotaionOriginalMap(pq.peek().x, pq.peek().y, pq.peek().d);

			List<Position> list = pq.peek().list;
			// 정렬
			Collections.sort(list, new Comparator<Position>() {

				@Override
				public int compare(Position o1, Position o2) {
					
					if(o1.x == o2.x) {
						return o2.y -o1.y;
					}
					else {
						return o1.x - o2.x;
					}
				}
			});
			
			// 가져온 지점의 방향을 기준으로 map에서 해당 지점의 유물들을 제거한다.
			// 유저 벽면의 잇는 조각을 다시 map에 집어넣는다.
			for(Position p : list) {
				map[p.y][p.x] = dummy[pickIdx++];
				//System.out.println(map[p.y][p.x]);
				pickIdx = pickIdx % m;
			}
			
			answer += pq.peek().point;
			
			
			
		} else {
			return;
		}
		
		// 연쇄폭발 확인하기
		while(true) {
			boolean visited[][] = new boolean[5][5];
			Queue<Position> q = new ArrayDeque<>();
			List<Position> boomList = new ArrayList<>();
			List<Position> list = new ArrayList<>();
			
			for(int y = 0; y < 5; y++) {
				for(int x = 0; x < 5; x++) {
					
					if(!visited[y][x]) {
						list.clear();
						
						// 현재 지점에서 bfs를 시작한다.
						q.add(new Position(x,y));
						list.add(new Position(x, y));
						visited[y][x] = true;
						int num = map[y][x];
						
						while(!q.isEmpty()) {
							Position p = q.poll();
							
							for(int i = 0; i < 4; i++) {
								int nx = p.x + dirX[i];
								int ny = p.y + dirY[i];
								
								if(isRange(nx, ny) && !visited[ny][nx] && map[ny][nx] == num) {
									q.add(new Position(nx, ny));
									list.add(new Position(nx, ny));
									visited[ny][nx] = true;;
								}
							}
						} // bfs 종료
						
						// 결과적으로 이어진 유물이 3개 이상인 경우에만 진행
						if(list.size() >= 3) {
							for(Position p : list) {
								boomList.add(p);
							}
						}
					}
					
				}
			}
			
			// 연쇄유물 3개 이상이면 터트린다.
			if(boomList.size() >= 3) {
				// 정렬
				Collections.sort(boomList, new Comparator<Position>() {

					@Override
					public int compare(Position o1, Position o2) {
						
						if(o1.x == o2.x) {
							return o2.y -o1.y;
						}
						else {
							return o1.x - o2.x;
						}
					}
				});
				
				for(Position p : boomList) {
					map[p.y][p.x] = dummy[pickIdx++];
					//System.out.println(map[p.y][p.x]);
					pickIdx = pickIdx % m;
				}
			}
			
			answer += boomList.size();
			if(boomList.size() == 0) break;
			
		}
		
		
	}
	
	public static void rotaionOriginalMap(int x, int y, int cnt) {
		// 중앙 지점에서 8방면을 배열로 기록해둔다.
		int[] eight = new int[8];
		int[] tmp = new int[8];
		
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			tmp[i] = map[ny][nx];
		}
		
		// 90도로 cnt만큼 회전시켜서 copyMap에 저장한다
		// cnt * 2만큼 index를 바꿔서 저장하면 됨
		for(int i = 0; i < 8; i++) {
			int idx = (i+2*cnt) % 8;
			eight[idx] = tmp[i];
		}
		
		// 중심점 주변을 회전시켜준다.
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			map[ny][nx] = eight[i];
		}
	}
	
	// 파라미터로 중심좌표와 회전 횟수를 파라미터로 받는다. 큐브를 회전시킨 후 2차원 배열을 반환한다.
	public static int[][] rotation90(int x, int y, int cnt) {
		int[][] copyMap = new int[5][5];
		// map을 복사해서 copyMap에 저장한다.
		for(int i = 0; i < 5; i++) {
			copyMap[i] = map[i].clone();
		}
		
		// 중앙 지점에서 8방면을 배열로 기록해둔다.
		int[] eight = new int[8];
		int[] tmp = new int[8];
		
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			tmp[i] = copyMap[ny][nx];
		}
		
		// 90도로 cnt만큼 회전시켜서 copyMap에 저장한다
		// cnt * 2만큼 index를 바꿔서 저장하면 됨
		for(int i = 0; i < 8; i++) {
			int idx = (i+2*cnt) % 8;
			eight[idx] = tmp[i];
		}
		
		// 중심점 주변을 회전시켜준다.
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			copyMap[ny][nx] = eight[i];
		}
		
		return copyMap;
	}
	
	// 파라미터로 우선순위큐를 받아 탐색을 통해 찾은 유물개수와 지워야할 유물이 표시된 부분이 기록된 2차원 배열을 표시한 포인트를 우선순위 큐에 추가한다.
	public static void bfs(int[][] copyMap, int centerX, int centerY, int d) {
		
		int cnt = 0;
		boolean visited[][] = new boolean[5][5];
		Queue<Position> q = new ArrayDeque<>();
		List<Position> list = new ArrayList<>();
		Point point = new Point(centerX, centerY, d);
		
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 5; x++) {
				
				if(!visited[y][x]) {
					list.clear();
					
					// 현재 지점에서 bfs를 시작한다.
					q.add(new Position(x,y));
					list.add(new Position(x, y));
					visited[y][x] = true;
					int num = copyMap[y][x];
					
					while(!q.isEmpty()) {
						Position p = q.poll();
						
						for(int i = 0; i < 4; i++) {
							int nx = p.x + dirX[i];
							int ny = p.y + dirY[i];
							
							if(isRange(nx, ny) && !visited[ny][nx] && copyMap[ny][nx] == num) {
								q.add(new Position(nx, ny));
								list.add(new Position(nx, ny));
								visited[ny][nx] = true;;
							}
						}
					} // bfs 종료
					
					// 결과적으로 이어진 유물이 3개 이상인 경우에만 진행
					if(list.size() >= 3) {
						point.point += list.size();
						
						for(Position p : list) {
							point.list.add(p);
						}
						
					}
					
					
				}
				
			}
		}
		
		pq.add(point);
		
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= 5 || y < 0 || y >= 5) return false;
		return true;
	}
	
	
	
}