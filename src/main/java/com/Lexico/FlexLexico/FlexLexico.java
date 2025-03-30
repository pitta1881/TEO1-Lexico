package com.Lexico.FlexLexico;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

public class FlexLexico {

	public static TokenRulesObject analizar(String inputText) throws Exception {
		StringReader stringReader = new StringReader(inputText);
		Lexico Lexer = new Lexico(stringReader);
		parser sintactico = new parser(Lexer);
		TokenRulesNodeObject tokenRulesNodeObject = (TokenRulesNodeObject) sintactico.parse().value;
		try {
			FileWriter archivo = new FileWriter("arbol.dot");
			PrintWriter pw = new PrintWriter(archivo);
			pw.println(tokenRulesNodeObject.nodeProgram().graph());
			archivo.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return new TokenRulesObject(tokenRulesNodeObject.tokenList(), tokenRulesNodeObject.rulesList());
	}

}
