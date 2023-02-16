package com.zhy.other.layui;

import com.zhy.other.layui.entity.TreeNode;
import com.zhy.other.layui.factory.TreeFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhouhongyin
 * @since 2021/8/26 9:09
 */
@RestController
@RequestMapping("LayuiController")
@CrossOrigin
public class LayuiController {

    @GetMapping("layuiTree")
    public List<TreeNode> layuiTree(){
        List<TreeNode> nodeList = TreeFactory.LayuiTreeFactory();

        List<TreeNode> layuiTree = layuiTreeFactory(nodeList);

        return layuiTree;

    }

    public List<TreeNode> layuiTreeFactory(List<TreeNode> nodeList){

        List<TreeNode> layuiTree = new ArrayList<>();

        for (TreeNode treeNode : nodeList) {

            for (TreeNode node : nodeList) {

                if (node.getPid().equals(treeNode.getId())){
                    treeNode.getChildren().add(node);
                }
            }

            if (treeNode.getPid().equals("0")) {
                layuiTree.add(treeNode);
            }

        }

        return layuiTree;
    }

    @GetMapping("layuiTreeTable")
    public List<TreeNode> layuiTreeTable(){

        List<TreeNode> layuiTreeTable = TreeFactory.LayuiTreeFactory();

        return layuiTreeTable;

    }

}
