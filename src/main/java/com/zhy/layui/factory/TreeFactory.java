package com.zhy.layui.factory;

import com.zhy.layui.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/8/26 9:46
 */
public class TreeFactory {

    public static List<TreeNode> LayuiTreeFactory(){

        List<TreeNode> tree = new ArrayList<>();

        TreeNode node1 = new TreeNode("1","江西", "0","test");
        TreeNode node2 = new TreeNode("2","陕西", "0","test");

        TreeNode node3 = new TreeNode("3","南昌", "1","test");
        TreeNode node4 = new TreeNode("4","西安", "2","test");

        TreeNode node5 = new TreeNode("5","高新区", "3","test");
        TreeNode node6 = new TreeNode("6","高新区2", "3","test");

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);

        return tree;

    }


}
