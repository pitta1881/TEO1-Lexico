package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeWhile extends Node {
    public final NodeLogical logical;
    public final List<NodeSentence> blockBody;
    private Node nodeBody;
 
    public NodeWhile(NodeLogical logical, List<NodeSentence> blockBody) {
        super("WHILE");
        this.nodeBody = new Node("BODY");
        this.logical = logical;
        this.blockBody = blockBody;
    }

    @Override
    protected String graph(String idPadre) {
        final String myId = this.getIdNode();
        return super.graph(idPadre) +
            this.logical.graph(myId) +
            this.nodeBody.graph(myId) +
            this.blockBody.stream().map(sentence -> sentence.graph(this.nodeBody.getIdNode())).reduce("", String::concat);
    }

}