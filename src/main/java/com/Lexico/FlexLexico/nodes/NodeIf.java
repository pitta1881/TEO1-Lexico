package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeIf extends Node {
    public final NodeLogical logical;
    public final List<NodeSentence> blockThen;
    public final List<NodeSentence> blockElse;
    private Node nodeThen;
    private Node nodeElse;
 
    public NodeIf(NodeLogical logical, List<NodeSentence> blockThen, List<NodeSentence> blockElse) {
        super("IF");
        this.nodeThen = new Node("THEN");
        this.nodeElse = new Node("ELSE");
        this.logical = logical;
        this.blockThen = blockThen;
        this.blockElse = blockElse;
    }

    @Override
    protected String graph(String idPadre) {
        final String myId = this.getIdNode();
        return super.graph(idPadre) +
            this.logical.graph(myId) +
            this.nodeThen.graph(myId) +
            this.blockThen.stream().map(sentence -> sentence.graph(this.nodeThen.getIdNode())).reduce("", String::concat) +
            (this.blockElse != null ? this.nodeElse.graph(myId) : "") +
            (this.blockElse != null ? this.blockElse.stream().map(sentence -> sentence.graph(this.nodeElse.getIdNode())).reduce("", String::concat) : "");
    }

    @Override
    public String assembly() {
        String etiquetaThen = "ThenIf_" + Integer.toHexString(this.hashCode());
        String etiquetaElse = this.blockElse != null ? "ElseIf_" + Integer.toHexString(this.hashCode()) : null;
        String etiquetaEnd = "EndIf_" + Integer.toHexString(this.hashCode());

        String comparatorAssembled = this.logical.assemblyCustom(etiquetaThen, etiquetaElse, etiquetaEnd);
        String thenBlockAssembled = this.blockThen.stream().map(sentence -> sentence.assembly()).reduce("", String::concat);
        String elseBlockAssembled = this.blockElse != null ? this.blockElse.stream().map(sentence -> sentence.assembly()).reduce("", String::concat) : "";

        return comparatorAssembled +
                etiquetaThen + ": " + "\n" +
                thenBlockAssembled +
                "jmp " + etiquetaEnd + "\n" +
                (this.blockElse != null ? 
                    etiquetaElse + ": " + "\n" +
                    elseBlockAssembled : "") +
                etiquetaEnd + ": " + "\n";
    }

}