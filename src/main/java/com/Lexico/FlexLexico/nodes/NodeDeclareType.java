package com.Lexico.FlexLexico.nodes;

public class NodeDeclareType extends NodeBinaryExpression {

    public NodeDeclareType(NodeExpression left, NodeExpression right) {
        super(":=", left, right);
    }
}