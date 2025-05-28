package com.Lexico.FlexLexico.nodes;

import com.Lexico.FlexLexico.store.StoreDeclaredVars;

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

    @Override
    protected String assembly() {
        String instructionType = StoreDeclaredVars.get(left.getDescriptionNode()).type().get();
        String value = right.getDescriptionNode().replace('.', '_');
        String instruction;
        if (Boolean.TRUE.equals(isID(right))) {
            instruction = instructionType.equals("INT") ? "fild " : "fld ";
        } else {
            instruction = instructionType.equals("INT") ? "fild _" : "fld _";
        }
        String instructionStore = instructionType.equals("INT") ? "fistp " : "fstp ";
        return instruction + value + "\n" +
                instructionStore + left.getDescriptionNode() + "\n";
    }

    private Boolean isID(Node node) {
        return node != null && node.getDescriptionNode().matches(".*[a-zA-Z].*");
    }
}