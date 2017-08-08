package com.saille.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XMLTreeNode {
    private String name;
    private XMLTreeNode parent;
    private List<XMLTreeNode> children;
    private Map<String, Object> attributes;
    private String value;
    private int level;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XMLTreeNode getRootNode() {
        if(this.level == 0) {
            return this;
        }
        return this.parent.getRootNode();
    }

    public XMLTreeNode getParent() {
        return this.parent;
    }

    public void setParent(XMLTreeNode parent) {
        this.parent = parent;
        this.level = (parent.getLevel() + 1);
    }

    public List<XMLTreeNode> getChildren() {
        return this.children;
    }

    public void setChildren(List<XMLTreeNode> children) {
        this.children = children;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLevel() {
        return this.level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public void addAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public void addChild(XMLTreeNode node) {
        if(this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(node);
    }
}