package com.Lexico.FlexLexico.nodes;

import java.util.List;

public class NodeDeclarationSection extends Node {
    private final List<NodeDeclareType> declareTypeList;

    public NodeDeclarationSection(List<NodeDeclareType> declareTypeList) {
        super("DECLARE SECTION");
        this.declareTypeList = declareTypeList;
    }

    @Override
    protected String graph(String idPadre) {
        return super.graph(idPadre) + this.declareTypeList.stream().map(declareType -> declareType.graph(this.getIdNode())).reduce("", String::concat);
    }
}

