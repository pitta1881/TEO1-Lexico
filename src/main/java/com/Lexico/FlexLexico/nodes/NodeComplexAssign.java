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

    @Override
    protected String assembly() {
        String value = right.getDescriptionNode().replace('.', '_');
        String instruction;
        if (Boolean.TRUE.equals(isID(right))) {
            instruction = Boolean.TRUE.equals(isFloat(right)) ? "fld " : "fild ";
        } else {
            instruction = Boolean.TRUE.equals(isFloat(right)) ? "fld _" : "fild _";
        }
        return instruction + value + "\n" +
                "fstp " + left.getDescriptionNode() + "\n";
    }


    private Boolean isFloat(Node node) {
        return node != null && (node.getDescriptionNode().contains(".") || node.getDescriptionNode().matches(".*[a-zA-Z].*"));
    }

    private Boolean isID(Node node) {
        return node != null && node.getDescriptionNode().matches(".*[a-zA-Z].*");
    }
}