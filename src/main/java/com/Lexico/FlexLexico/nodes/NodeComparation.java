package com.Lexico.FlexLexico.nodes;

public class NodeComparation extends Node {

    public final NodeArithmetic left;
    public final NodeArithmetic right;

    public NodeComparation(Node relationalComparator, NodeArithmetic left, NodeArithmetic right) {
        super(relationalComparator.getDescriptionNode());
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

    @Override
    public String assembly() {
        
        return "";
    }
}