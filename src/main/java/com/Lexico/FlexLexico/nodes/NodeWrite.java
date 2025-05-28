package com.Lexico.FlexLexico.nodes;

import java.util.List;

import com.Lexico.FlexLexico.store.StoreMessages;

public class NodeWrite extends Node {

    private Node nodeText;
    private String type;
    private String keyIfString = null;

    public NodeWrite(Node nodeText, String type) {
        super("WRITE");
        this.nodeText = nodeText;
        this.type = type;
        if (type.equals("STRING")) {
            keyIfString = "message_" + Integer.toHexString(this.hashCode());
            StoreMessages.setMessage(keyIfString, nodeText.getDescriptionNode());
        }
    }

    @Override
    protected String graph(String idPadre) {
        final String myId = this.getIdNode();
        return super.graph(idPadre) + nodeText.graph(myId);
    }

    @Override
    public String assembly() {
        String displayWrite;
        if (this.type.equals("FLOAT")) {
            displayWrite = "displayFloat _" + nodeText.getDescriptionNode().replace('.', '_') + ", 2\n";
            displayWrite = Boolean.TRUE.equals(isID(nodeText)) ? displayWrite.replace("_", "") : displayWrite;
        } else if (this.type.equals("STRING")) {
            displayWrite = "displayString " + keyIfString + "\n";
        } else {
            displayWrite = "displayInteger _" + nodeText.getDescriptionNode() + "\n";
            displayWrite = Boolean.TRUE.equals(isID(nodeText)) ? displayWrite.replace("_", "") : displayWrite;
        }
        return displayWrite +
            "newLine 1\n";
    }

    private Boolean isID(Node node) {
        return node != null && node.getDescriptionNode().matches(".*[a-zA-Z].*");
    }

}