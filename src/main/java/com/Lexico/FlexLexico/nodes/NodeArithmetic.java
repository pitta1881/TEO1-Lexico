package com.Lexico.FlexLexico.nodes;

public class NodeArithmetic extends Node {
    
    public final Node left;
    public final Node right;
    public final String operator;
    public final Node factor;

    public NodeArithmetic(String operator, NodeArithmetic right, Node left) {
        super(operator);
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.factor = null;
    }
    
    public NodeArithmetic(Node factor) {
        super(factor.getDescriptionNode());
        this.left = null;
        this.right = null;
        this.operator = null;
        this.factor = factor;
    }

    @Override
    protected String graph(String idPadre) {
        final String myId = this.getIdNode();
        if (factor != null) {
            return super.graph(idPadre);
        }
        return super.graph(idPadre) +
                right.graph(myId) +
                left.graph(myId);
    }

}
