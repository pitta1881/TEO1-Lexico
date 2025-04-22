package com.Lexico.FlexLexico.nodes;

public class NodeComplexAssign extends Node {

    public final Node left;
    public final Node right;

    public NodeComplexAssign(Node left, Node right) {
        super("=");
        this.left = left;
        this.right = right;
    }

    @Override
    protected String graph(String idPadre) {
        final String myId = this.getIdNode();
        return super.graph(idPadre) +
                left.graph(myId) +
                right.graph(myId);
    }
}