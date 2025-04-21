package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeStart extends Node {
    private final List<NodeSentence> sentences;

    public NodeStart(List<NodeSentence> sentences) {
        super("START");
        this.sentences = sentences;
    }

    public String graph() {
        // Acá se dispara la invocación a los métodos graficar() de los nodos.
        // Como un NodeProgram no tiene padre, se inicia pasando null.
        return this.graph(null);
    }

    @Override
    protected String graph(String idPadre) {
        final String miId = "node_start";
        StringBuilder result = new StringBuilder();

        result.append("graph G {");
        result.append(miId + " [label=\"Start\"]\n");
        result.append(this.sentences.stream().map(sentence -> sentence.graph(miId)).reduce("", String::concat));
        result.append("}");

        return result.toString();
    }
}

