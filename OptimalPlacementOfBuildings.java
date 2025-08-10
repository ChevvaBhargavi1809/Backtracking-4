// Time Complexity : O((h*w)Cn)
// Space Complexity : O(h*w)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Approach : find exhaustively all posisble combinations. For each combination of buildings, find the max dist of farthest lot using bfs 

/*
    Given a grid with w as width, h as height. Each cell of the grid represents a potential building lot and we will be adding "n" buildings inside this grid. The goal is for the furthest of all lots to be as near as possible to a building. Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to minimize the distance the most distant empty lot is from the building. Movement is restricted to horizontal and vertical i.e. diagonal movement is not required.

For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.

"0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".

1 0 1 2

2 1 2 1

1 0 1 0

2 1 2 1
*/
import java.util.*;
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        OptimalBuildings optimal = new OptimalBuildings(4, 4, 3);
        System.out.println(optimal.getOptimalPlacement());
    }
}
class OptimalBuildings{
    int h, w, n;
    boolean grid[][];
    int min;
    OptimalBuildings(int h, int w, int n){
        this.h = h;
        this.w = w;
        this.n = n;
        grid = new boolean[h][w];
        min = Integer.MAX_VALUE;
    }
    public int getOptimalPlacement(){
        backtrack(0, this.n);
        System.out.println("result = "+this.min);
        return this.min;
    } 
    private void backtrack(int idx, int num){
        if(num==0){
            //System.out.println("reached base case");
            bfs(grid);
            //System.out.println("min = "+this.min);
            return;
        }
        for(int i=idx;i<this.h*this.w;i++){
            int r = i/this.w;
            int c = i%this.w;
            grid[r][c] = true;
            backtrack(idx+1, num-1);
            grid[r][c] = false;
        }
    }
    private void bfs(boolean grid[][]){
        Queue<int[]> q = new LinkedList<>();
        boolean vis[][] = new boolean[grid.length][grid[0].length];
        for(int i=0;i<this.h;i++){
            for(int j=0;j<this.w;j++){
                if(grid[i][j]==true){
                    q.add(new int[]{i,j});
                    vis[i][j] = true;
                }
            }
        }
        int dirs[][] = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int dist = 0;
        while(q.size()!=0){
            int size = q.size();
            for(int s=0;s<size;s++){
                int[] polled = q.poll();
                int r = polled[0];
                int c = polled[1];
                //check neighbors if in bounds add to list;
                for(int dir[]:dirs){
                    int nr = r+dir[0];
                    int nc = c+dir[1];
                    if(nr>=0 && nc>=0 && nr<this.h && nc<this.w && !vis[nr][nc]){
                        q.add(new int[]{nr, nc});
                        vis[nr][nc] = true;
                    }
                }
            }
            //System.out.println(dist);
            dist++;
        }
        //System.out.println("returning dist "+dist);
        this.min = Math.min(this.min,dist-1);
    }
}