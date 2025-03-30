package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeStart extends Node {
    private final List<NodeDeclareType> declareTypeList;
    private final NodeProgramSection nodeProgramSection;

    public NodeStart(List<NodeDeclareType> declareTypeList, NodeProgramSection nodeProgramSection) {
        super("START");
        this.declareTypeList = declareTypeList;
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
        result.append(this.declareTypeList.stream().map(declareType -> declareType.graph(miId)).reduce("", String::concat));
        result.append(this.nodeProgramSection.graph(miId));
        result.append("}");

        return result.toString();
    }
}

