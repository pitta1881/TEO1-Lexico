package com.Lexico.FlexLexico;

import com.Lexico.FlexLexico.nodes.NodeStart;

import java.util.ArrayList;

public record TokenRulesNodeObject(ArrayList<TokenObject> tokenList, ArrayList<RuleObject> rulesList, NodeStart nodeProgram) {}
