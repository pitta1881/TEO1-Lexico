package com.Lexico.FlexLexico.nodes;

public class NodeBinaryExpression extends NodeExpression {
    public final NodeExpression left;
    public final NodeExpression right;

    public NodeBinaryExpression(String nombre, NodeExpression left, NodeExpression right) {
        super(nombre);
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
