import java.util.*;
import java.io.*;

class Position {
	int x, y;
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Knight {
	int id;
	int hp;
	int accumDamage = 0;
	boolean isOut = false;
	List<Position> positions = new ArrayList<>();
	
	Knight(int id, int hp) {
		this.id = id;
		this.hp = hp;
	}
	
	public void addDamage(int damage) {
		this.hp -= damage;
		this.accumDamage += damage;
	}	
	
	public void setInitPosition(int initX, int initY, int h, int w, int[][] knightMap) {
		
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int nx = initX + x;
				int ny = initY + y;
				// h * w 만큼의 위치를 세팅
				positions.add(new Position(nx, ny));
				knightMap[ny][nx] = this.id;
			}
		}
		
	}
	
	public void dead(int[][] knightMap) {
		this.isOut = true;
		
		for(Position pos : positions) {
			knightMap[pos.y][pos.x] = 0;
		}
		
	}
	
	public void clearCurrentPosition(int[][] knightMap) {
		for(Position pos : positions) {
			if(knightMap[pos.y][pos.x] == this.id) {
				knightMap[pos.y][pos.x] = 0;
			}
		}
		
	}
	
}

public class Main {
	
	static int l,n,q;
	static int[][] map;
	static int[][] knightMap;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static List<Knight> list = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		map = new int[l][l];
		knightMap = new int[l][l];
		
		for(int y = 0; y < l; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < l; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			
			// 기사의 idx 와 기사 hp를 세팅합니다.
			Knight knight = new Knight(i+1, hp);
			
			// 기사 위치를 세팅합니다.
			// 기사 위치를 기사지도에 표시합니다.
			knight.setInitPosition(x-1, y-1, h, w, knightMap);
			
			// 기사 목록에 추가합니다.
			list.add(knight);
		}
		
		// i-1번 기사에게 d방향으로 이동하는 명령
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			// 움직일 범위를 백트래킹을 활용해 탐색한다.
			moveCheck(idx, d);
			
			// 벽에 걸리지 않았으면 set에는 기사들이 들어가 있을 것이다.
			doMove(idx, d);
			
			// set은 초기화해준다.
			set.clear();
		}
		
		int ans = 0;
		for(Knight knight : list) {
			if(!knight.isOut) ans += knight.accumDamage;
		}
		System.out.println(ans);
	}
	
	public static void doMove(int firstNight, int d) {
		
		// set에는 움직이는 기사들이 포함되어 있다.
		for(int idx : set) {
			Knight knight = list.get(idx);
			knight.clearCurrentPosition(knightMap);
			for(Position pos : knight.positions) {
				int nx = pos.x + dx[d];
				int ny = pos.y + dy[d];
				
				// 기사 지도 위치 업데이트
				knightMap[ny][nx] = knight.id;
				
				// 밀고 있는 기사가 아니라면 덫 데미지 추가
				if(idx !=  firstNight) {
					if(map[ny][nx] == 1 ) {
						knight.addDamage(1);
						//System.out.println(knight.id + " 덫 밟음 " + knight.hp);
						
						if(knight.hp == 0) {
							// 기사가 죽은 경우에는 기사지도에서 해당 기사를 지우고 죽은걸로 처리한다.
							knight.dead(knightMap);
							break;
						}
					}
				}
				pos.x = nx;
				pos.y = ny;
				
			} // 포지션 이동 종료 기사가 죽은 경우에도 종료
		}
	}
	
	public static void moveCheck(int idx, int d) {
		// 현재 기사가 지도에서 아웃된 기사면 움직이지 않는다.
		if(list.get(idx).isOut) return;
		
		// 움직일 기사 목록에 추가한다.
		set.add(idx);
		
		// 탐색
		List<Position> posList = list.get(idx).positions;
		
		for(Position pos : posList) {
			int nx = pos.x + dx[d];
			int ny = pos.y + dy[d];
			
			// 움직일 범위가 지도 바깥이라면 아웃처리하고 더 이상 진행하지 않는다.
			if(!isRange(nx, nx)) {
				list.get(idx).dead(knightMap);
				set.remove(idx);
				return;
			}
			
			// 벽이라면 다 초기화하고 진행하지 않는다.
			if(map[ny][nx] == 2) {
				set.clear();
				return;
			}
			
			// 벽이 아니고 기사인 경우이면서 자신을 표시하는 경우가 아닌 경우 move와 해당 기사의 id를 재호출
			if(knightMap[ny][nx] != 0 && knightMap[ny][nx] != list.get(idx).id && !set.contains(knightMap[ny][nx]-1)) {
				moveCheck(knightMap[ny][nx]-1, d);
			}
		}
		
		
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= l || y < 0 || y >= l) return false;
		return true;
	}
	
	
	
}