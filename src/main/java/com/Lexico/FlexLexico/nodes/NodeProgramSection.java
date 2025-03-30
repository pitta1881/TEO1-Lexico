package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeProgramSection extends Node {
    private final List<NodeSentence> sentences;

    public NodeProgramSection(List<NodeSentence> sentences) {
        super("PROGRAM SECTION");
        this.sentences = sentences;
    }

    @Override
    protected String graph(String idPadre) {
        return super.graph(idPadre) + this.sentences.stream().map(sentence -> sentence.graph(this.getIdNode())).reduce("", String::concat);
    }
}

