package com.Lexico.FlexLexico.nodes;

import com.Lexico.FlexLexico.store.StoreDeclaredVars;

public class NodeSimpleAssign extends Node {

    public final Node left;
    public final NodeArithmetic right;

    public NodeSimpleAssign(Node left, NodeArithmetic right) {
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

    @Override
    protected String assembly() {
        String instructionType = StoreDeclaredVars.get(left.getDescriptionNode()).type().get();
        String rightAssembled = right.assemblyCustom(instructionType);
        String instruction = instructionType.equals("INT") ? "fistp " : "fstp ";
        return rightAssembled +
                instruction + left.getDescriptionNode() + "\n";
    }
}