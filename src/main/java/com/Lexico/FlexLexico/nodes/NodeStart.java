package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeStart extends Node {
    private final NodeDeclarationSection nodeDeclarationSection;
    private final NodeProgramSection nodeProgramSection;

    public NodeStart(NodeDeclarationSection nodeDeclarationSection, NodeProgramSection nodeProgramSection) {
        super("START");
        this.nodeDeclarationSection = nodeDeclarationSection;
        this.nodeProgramSection = nodeProgramSection;
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
        result.append(this.nodeDeclarationSection.graph(miId));
        result.append(this.nodeProgramSection.graph(miId));
        result.append("}");

        return result.toString();
    }
}

