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

    @Override
    public String assembly() {
        String displayString = listNodeText.get(0).getTypeNode().equals("FLOAT") ? 
                    "displayFloat _" + listNodeText.get(0).getDescriptionNode().replace('.', '_') + ", 2\n":
                    "displayInteger _" + listNodeText.get(0).getDescriptionNode() + "\n";
        displayString = Boolean.TRUE.equals(isID(listNodeText.get(0))) ? displayString.replace("_", "") : displayString;
        return displayString +
            "newLine 1\n";
    }

    private Boolean isID(Node node) {
        return node != null && node.getDescriptionNode().matches(".*[a-zA-Z].*");
    }

}