import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/15
 * Time: 16:30
 * Description:
 */
public class BipartitionDetection {

    /**
     * 图
     */
    private Graph G;

    /**
     * 标记是否被访问过的数组
     */
    private boolean[] visited;

    /**
     * 标记每个顶点是什么颜色的数组
     * -1：没有被访问过，初始化状态
     * 0：蓝色
     * 1：绿色
     */
    private int[] colors;

    /**
     * 是否通过二分图检测
     */
    private boolean isBipartite = false;

    /**
     * 构造函数
     * @param G 需要被遍历的图
     */
    public BipartitionDetection(Graph G) {
        this.G = G;
        //初始化标记数组，数组的大小为传入的图的顶点数
        visited = new boolean[G.V()];
        //初始化 colors 数组
        colors = new int[G.V()];
        Arrays.fill(colors, -1);
        //遍历所有没有被访问过的节点，避免连通分量大于1出现有节点没有被遍历到的情况
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    return;
                }
            }
        }
        isBipartite = true;
    }

    /**
     * 深度优先遍历，染色
     * @param v 顶点 v
     * @param color 顶点颜色
     * @return 二分图检测是否通过
     */
    private boolean dfs(int v, int color) {
        //标记节点 v 为已遍历的状态
        visited[v] = true;
        colors[v] = color;
        //递归遍历所有与节点 v 相连但是没有被遍历过的节点
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else {
                if (colors[w] == color) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 是否通过二分图检测
     * @return 二分图检测结果
     */
    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph g = new Graph("04-Graph-DFS-Applications/11-Bipartition-Detection/g3.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite());
    }

}
