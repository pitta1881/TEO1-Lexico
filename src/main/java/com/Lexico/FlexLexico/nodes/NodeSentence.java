package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeSentence extends Node {

    // private NodeSimpleAssign simpleAssign = null;
    private List<NodeComplexAssign> complexAssign = null;

    public NodeSentence(List<NodeComplexAssign> complexAssign) {
        super("sentence");
        this.complexAssign = complexAssign;
    }

    // public NodeSentence(NodeSimpleAssign simpleAssign) {
    //     super("sentence");
    //     this.simpleAssign = simpleAssign;
    // }

    public NodeSentence() {
        super("sentence");
    }

    @Override
    protected String graph(String idPadre) {
        // if (this.simpleAssign != null) {
        //     return this.simpleAssign.graph(idPadre);
        // } else
        if (this.complexAssign != null) {
            return this.complexAssign.stream().map(sentence -> sentence.graph(idPadre)).reduce("", String::concat);
        }
        return "";
    }
}
