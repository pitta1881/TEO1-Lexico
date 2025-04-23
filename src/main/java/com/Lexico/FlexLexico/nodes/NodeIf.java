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

}