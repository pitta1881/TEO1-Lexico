package com.Lexico.FlexLexico.nodes;

public class NodeSimpleAssign extends NodeBinaryExpression {

    public NodeSimpleAssign(NodeExpression left, NodeExpression right) {
        super("=", left, right);
    }
}