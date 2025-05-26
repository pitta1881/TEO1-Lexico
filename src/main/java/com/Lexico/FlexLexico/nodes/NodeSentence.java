package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeSentence extends Node {

    private NodeSimpleAssign simpleAssign = null;
    private List<NodeComplexAssign> complexAssign = null;
    private NodeIf nodeIf = null;
    private NodeWhile nodeWhile = null;
    private NodeWrite nodeWrite;

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
    
    public NodeSentence(NodeWhile nodeWhile) {
        super("sentence");
        this.nodeWhile = nodeWhile;
    }

    public NodeSentence(NodeWrite nodeWrite) {
        super("sentence");
        this.nodeWrite = nodeWrite;
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
        if (this.nodeWhile != null) {
            return this.nodeWhile.graph(idPadre);
        }
        if (this.nodeWrite != null) {
            return this.nodeWrite.graph(idPadre);
        }
        return "";
    }

    @Override
    public String assembly() {
        if (this.simpleAssign != null) {
            return this.simpleAssign.assembly() + "ffree\n";
        }
        if (this.complexAssign != null) {
            return this.complexAssign.stream()
                .map(assign -> assign.assembly() + "ffree\n")
                .reduce("", String::concat);
        }
        if (this.nodeIf != null) {
            return this.nodeIf.assembly() + "ffree\n";
        }
        if (this.nodeWhile != null) {
            return this.nodeWhile.assembly();
        }
        if (this.nodeWrite != null) {
            return this.nodeWrite.assembly();
        }
        return "";
    }
}
