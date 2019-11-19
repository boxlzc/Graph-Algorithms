import java.util.*;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/19
 * Time: 12:29
 * Description:
 */
public class SingleSourcePath {

    /**
     * 图 G
     */
    private Graph G;

    /**
     * 源点
     */
    private int s;

    /**
     * 标记访问的数组
     */
    private boolean[] visited;

    /**
     * 记录前一个顶点信息的数组
     */
    private int[] pre;

    /**
     * 构造函数，传入一个图
     * @param g 图 g
     * @param s 源点
     */
    public SingleSourcePath(Graph g, int s) {
        this.G = g;
        this.s = s;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        bfs(s);
    }

    /**
     * 广度优先遍历
     * @param s 顶点 s
     */
    public void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (queue.size() != 0) {
            Integer v = queue.poll();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }

    /**
     * 获取一条以 s 为起源，t 为目的地的路径
     * @param t 目的节点
     * @return 路径
     */
    public Iterable<Integer> path(int t) {
        List<Integer> res = new ArrayList<>();
        //如果 s 与 t 没有连接或者 t 不合法，直接返回空集合
        if (!isConnectedTo(t)) {
            return res;
        }
        //cur表示当前节点
        int cur = t;
        //如果 cur != s ,证明还没有找到一条路径，继续循环，直到找到一条路径位置
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        //将 s 添加进集合
        res.add(s);
        //反转集合，获得正向路径
        Collections.reverse(res);
        return res;
    }

    /**
     * 判断顶点 t 是否与源点 s 存在路径
     * @param t 顶点 t
     * @return 是否与源点 s 存在路径
     */
    private boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public static void main(String[] args){
        Graph g = new Graph("05-Graph-BFS/03-Single-Source-Path-BFS/g.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(g, 1);
        System.out.println("BFS 1->6 :" + singleSourcePath.path(6));
    }

}
