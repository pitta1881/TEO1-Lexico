package com.Lexico.FlexLexico.nodes;

public class NodeLogical extends Node {

    public final NodeComparation left;
    public final NodeComparation right;
    public final NodeComparation nodeComparation;

    public NodeLogical(Node logicalComparator, NodeComparation left, NodeComparation right) {
        super(logicalComparator.getDescriptionNode());
        this.left = left;
        this.right = right;
        this.nodeComparation = null;
    }
    
    public NodeLogical(NodeComparation nodeComparation) {
        super(nodeComparation.getDescriptionNode());
        this.left = null;
        this.right = null;
        this.nodeComparation = nodeComparation;
    }

    @Override
    protected String graph(String idPadre) {
        final String myId = this.getIdNode();
        if (this.nodeComparation != null) {
            return this.nodeComparation.graph(idPadre);
        }
        return super.graph(idPadre) +
                left.graph(myId) +
                right.graph(myId);
    }
}