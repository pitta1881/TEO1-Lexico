package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeWrite extends Node {

    private List<Node> listNodeText;

    public NodeWrite(List<Node> listNodeText) {
        super("WRITE");
        this.listNodeText = listNodeText;
    }

    @Override
    protected String graph(String idPadre) {
        final String myId = this.getIdNode();
        return super.graph(idPadre) + 
            listNodeText.stream()
                .sorted((a, b) -> -1) // Reverse the list
                .map(node -> node.graph(myId))
                .reduce("", String::concat);
    }

}