import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/13
 * Time: 18:04
 * Description: 求两点之间是否存在一条路径
 */
public class Path {

    /**
     * 图
     */
    private Graph G;
    /**
     * 标记是否被访问过的数组
     */
    private boolean[] visited;
    /**
     * 记录每个顶点它的前一个节点是谁的数据
     */
    private int[] pre;
    /**
     * 路径起源
     */
    private int s;
    /**
     * 路径目的
     */
    private int t;

    /**
     * 构造函数
     * @param G 需要被遍历的图
     * @param s 单源路径的[源]
     * @param s 单源路径的[目的]
     */
    public Path(Graph G, int s, int t) {
        this.G = G;
        this.s = s;
        this.t = t;
        //初始化标记数组，数组的大小为传入的图的顶点数
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        dfs(s, s);
    }

    /**
     * 判断 s 点与 t 点是否相连，即是否存在一条路径
     * @return 是否存在一条路径
     */
    public boolean isConnected() {
        G.validateVertex(t);
        return visited[t];
    }

    /**
     * 获取一条以 s 为起源，t 为目的地的路径
     * @return 路径
     */
    public Iterable<Integer> path() {
        List<Integer> res = new ArrayList<>();
        //如果 s 与 t 没有连接或者 t 不合法，直接返回空集合
        if (!isConnected()) {
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
     * 深度优先遍历
     * @param v 当前遍历的节点
     * @param parent 它的上一个节点
     */
    private boolean dfs(int v, int parent) {
        //标记节点 v 为已遍历的状态
        visited[v] = true;
        //记录上一个顶点的信息
        pre[v] = parent;
        if (v == t) return true;
        //递归遍历所有与节点 v 相连但是没有被遍历过的节点
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }
        return false;
    }

}
