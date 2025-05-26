package com.Lexico.FlexLexico.nodes;

public class Node {
    private final String description;
    private final String type;

    public Node(String description) {
        this.description = description;
        this.type = "STRING";
    }

    public Node(String description, String type) {
        this.description = description;
        this.type = type;
    } 

    protected String getIdNode() {
        return "nodo_" + this.hashCode();
    }

    public String getDescriptionNode() {
        return description;
    }

    public String getTypeNode() {
        return type;
    }

    protected String graph(String idPadre) {
        return String.format("%1$s [label=\"%2$s\"]\n%3$s -- %1$s\n", getIdNode(), getDescriptionNode(), idPadre);
    }

    protected String assembly() {
        return String.format("%s: %s\n", getIdNode(), getDescriptionNode());
    }
}