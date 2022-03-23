package com.zhy.layui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * @author zhouhongyin
 * @since 2021/8/26 9:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    private String id;

    private String title;

    private String pid;

    private String test;

    private List<TreeNode> children;

    public TreeNode(String id, String title, String pid, String test) {
        this.id = id;
        this.title = title;
        this.pid = pid;
        this.test = test;
        this.children = new ArrayList<>();
    }
}
