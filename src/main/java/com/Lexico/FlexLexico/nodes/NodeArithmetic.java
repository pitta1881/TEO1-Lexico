package com.Lexico.FlexLexico.nodes;

public class NodeArithmetic extends Node {
    
    public final Node left;
    public final Node right;
    public final String operator;
    public final Node factor;

    public NodeArithmetic(String operator, NodeArithmetic right, NodeArithmetic left) {
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

    @Override
    protected String assembly() {
        if (factor != null) {
            Boolean isFloat = factor.getDescriptionNode().contains(".") || factor.getDescriptionNode().matches(".*[a-zA-Z].*");
            String instruction = Boolean.TRUE.equals(isFloat) ? "fld _" : "fild _";
            instruction = factor.getDescriptionNode().matches(".*[a-zA-Z].*") ? instruction.replace("_", "") : instruction;
            return instruction + factor.getDescriptionNode().replace('.', '_') + "\n";
        }
        String operatorInstruction = switch (operator) {
            case "+" -> "fadd";
            case "-" -> "fsub";
            case "*" -> "fmul";
            case "/" -> "fdiv";
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
        return right.assembly() +
                left.assembly() +
                operatorInstruction + "\n";
    }

}
