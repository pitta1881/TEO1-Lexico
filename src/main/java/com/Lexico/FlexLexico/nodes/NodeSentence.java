package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeSentence extends Node {

    private NodeSimpleAssign simpleAssign = null;
    private List<NodeComplexAssign> complexAssign = null;
    private NodeIf nodeIf = null;

    public NodeSentence(List<NodeComplexAssign> complexAssign) {
        super("sentence");
        this.complexAssign = complexAssign;
    }

    public NodeSentence(NodeSimpleAssign simpleAssign) {
        super("sentence");
        this.simpleAssign = simpleAssign;
    }
    
    public NodeSentence(NodeIf nodeIf) {
        super("sentence");
        this.nodeIf = nodeIf;
    }

    public NodeSentence() {
        super("sentence");
    }

    @Override
    protected String graph(String idPadre) {
        if (this.simpleAssign != null) {
            return this.simpleAssign.graph(idPadre);
        }
        if (this.complexAssign != null) {
            return this.complexAssign.stream().map(sentence -> sentence.graph(idPadre)).reduce("", String::concat);
        }
        if (this.nodeIf != null) {
            return this.nodeIf.graph(idPadre);
        }
        return "";
    }
}
