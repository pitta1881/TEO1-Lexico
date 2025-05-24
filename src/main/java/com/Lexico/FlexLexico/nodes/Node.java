package com.Lexico.FlexLexico.nodes;

public class Node {
    private final String description;

    public Node(String description) {
        this.description = description;
    }

    protected String getIdNode() {
        return "nodo_" + this.hashCode();
    }

    public String getDescriptionNode() {
        return description;
    }

    protected String graph(String idPadre) {
        return String.format("%1$s [label=\"%2$s\"]\n%3$s -- %1$s\n", getIdNode(), getDescriptionNode(), idPadre);
    }

    protected String assembly() {
        return String.format("%s: %s\n", getIdNode(), getDescriptionNode());
    }
}