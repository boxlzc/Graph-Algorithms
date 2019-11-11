import java.util.ArrayList;
import java.util.List;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/11
 * Time: 23:22
 * Description: 树的深度优先遍历（先序遍历）
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}

public class TreeDFS {

    /**
     * 保存结果的集合
     */
    private List<Integer> list = new ArrayList<>();

    /**
     * 先序遍历给定节点
     * @param root 给定节点
     * @return 遍历结果的集合
     */
    public List<Integer> preOrderTraversal(TreeNode root) {
        preOrder(root);
        return list;
    }

    /**
     * 先序遍历给定节点
     * @param node 给定节点
     */
    private void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

}
