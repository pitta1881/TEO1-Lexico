package com.Lexico.FlexLexico.nodes;

public class NodeArithmetic extends Node {
    
    public final NodeArithmetic left;
    public final NodeArithmetic right;
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
        return assemblyCustom("FLOAT");
    }

    protected String assemblyCustom(String instructionType) {
        if (factor != null) {
            String value = factor.getDescriptionNode().replace('.', '_');
            String instruction;
            if (Boolean.TRUE.equals(isID(factor))) {
                instruction = instructionType.equals("INT") ? "fild " : "fld ";
            } else {
                instruction = instructionType.equals("INT") ? "fild _" : "fld _";
            }
            return instruction + value + "\n";
        }
        String operatorInstruction = switch (operator) {
            case "+" -> "fadd";
            case "-" -> "fsub";
            case "*" -> "fmul";
            case "/" -> "fdiv";
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
        return right.assemblyCustom(instructionType) +
                left.assemblyCustom(instructionType) +
                operatorInstruction + "\n";
    }

    private Boolean isID(Node node) {
        return node != null && node.getDescriptionNode().matches(".*[a-zA-Z].*");
    }

}
