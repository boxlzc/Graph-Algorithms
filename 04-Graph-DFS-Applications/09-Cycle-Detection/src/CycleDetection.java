/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/13
 * Time: 18:48
 * Description:
 */
public class CycleDetection {

    /**
     * 图
     */
    private Graph G;
    /**
     * 标记是否被访问过的数组
     */
    private boolean[] visited;
    /**
     * 标记图中是否有环
     */
    private boolean hasCycle = false;

    /**
     * 构造函数
     * @param G 需要被遍历的图
     */
    public CycleDetection(Graph G) {
        this.G = G;
        //初始化标记数组，数组的大小为传入的图的顶点数
        visited = new boolean[G.V()];
        //遍历所有没有被访问过的节点，避免连通分量大于1出现有节点没有被遍历到的情况
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                //如果有环，将 hasCycle 标记为有环，同时结束循环
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    /**
     * 深度优先遍历寻找环
     * @param v 需要遍历的顶点
     * @param parent 它的上一个顶点
     * @return 是否有环
     */
    private boolean dfs(int v, int parent) {
        //标记节点 v 为已遍历的状态
        visited[v] = true;
        //递归遍历所有与节点 v 相连但是没有被遍历过的节点
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                //如果有环直接返回 true
                if (dfs(w, v)) {
                    return true;
                }
                //如果 w 被访问过且不是它的上一个节点，把 hasCycle 标记为 true
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回是否有环
     * @return 是否有环
     */
    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("03-Graph-DFS/05-Graph-DFS-Improvement/g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle);
    }

}
