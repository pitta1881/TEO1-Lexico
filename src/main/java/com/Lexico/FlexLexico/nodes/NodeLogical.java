package com.Lexico.FlexLexico.nodes;

public class NodeLogical extends Node {

    public final NodeComparation left;
    public final NodeComparation right;
    public final NodeComparation nodeComparation;
    private final Node logicalComparator;

    public NodeLogical(Node logicalComparator, NodeComparation left, NodeComparation right) {
        super(logicalComparator.getDescriptionNode());
        this.left = left;
        this.right = right;
        this.nodeComparation = null;
        this.logicalComparator = logicalComparator;
    }
    
    public NodeLogical(NodeComparation nodeComparation) {
        super(nodeComparation.getDescriptionNode());
        this.left = null;
        this.right = null;
        this.nodeComparation = nodeComparation;
        this.logicalComparator = null;
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

    @Override
    public String assembly() {
        return this.assemblyCustom(null, null, null);
    }

    public String assemblyCustom(String etiquetaThen, String etiquetaElse, String etiquetaEnd) {
        if (this.nodeComparation != null) {
           return singleCondition(etiquetaThen, etiquetaElse, etiquetaEnd, this.nodeComparation, true);
        } else {
            return multipleConditions(etiquetaThen, etiquetaElse, etiquetaEnd);
        }
    }

    private String singleCondition(String etiquetaThen, String etiquetaElse, String etiquetaEnd, NodeComparation nodeComparationTarget, Boolean inverse) {
         String jmpInstruction;
         if (Boolean.TRUE.equals(inverse)) {
             jmpInstruction = switch (nodeComparationTarget.getDescriptionNode()) {
                    case ">" -> "jbe";
                    case "<" -> "jae";
                    case ">=" -> "jb";
                    case "<=" -> "ja";
                    case "==" -> "jne";
                    case "!=" -> "je";
                    default -> throw new IllegalArgumentException("Unsupported logical operator: " + nodeComparationTarget.getDescriptionNode());
                };
         } else {
             jmpInstruction = switch (nodeComparationTarget.getDescriptionNode()) {
                    case ">" -> "ja";
                    case "<" -> "jl";
                    case ">=" -> "jae";
                    case "<=" -> "jle";
                    case "==" -> "je";
                    case "!=" -> "jne";
                    default -> throw new IllegalArgumentException("Unsupported logical operator: " + nodeComparationTarget.getDescriptionNode());
                };
            }
            String leftAssembled = nodeComparationTarget.left.assembly();
            String rightAssembled = nodeComparationTarget.right.assembly();

            String etiquetaTarget = Boolean.TRUE.equals(inverse) ? (etiquetaElse != null ? etiquetaElse : etiquetaEnd) : etiquetaThen;
            return  leftAssembled +
                    rightAssembled +
                    "fxch " + "\n" +
                    "fcom" + "\n" +
                    "fstsw ax\n" +
                    "sahf\n" +                 
                    jmpInstruction + " " + etiquetaTarget + "\n";
    }

    private String multipleConditions(String etiquetaThen, String etiquetaElse, String etiquetaEnd) {
        if (this.logicalComparator.getDescriptionNode().equals("AND")) {
            return singleCondition(etiquetaThen, etiquetaElse, etiquetaEnd, this.left, true) +
                   singleCondition(etiquetaThen, etiquetaElse, etiquetaEnd, this.right, true);
        } else {
            return singleCondition(etiquetaThen, etiquetaElse, etiquetaEnd, this.left, false) +
                   singleCondition(etiquetaThen, etiquetaElse, etiquetaEnd, this.right, true);
        }
    }
}