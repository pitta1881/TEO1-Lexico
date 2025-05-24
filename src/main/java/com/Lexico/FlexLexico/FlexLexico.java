package com.Lexico.FlexLexico;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.File;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

public class FlexLexico {

	public static TokenRulesObject analizar(String inputText) throws Exception {
		StringReader stringReader = new StringReader(inputText);
		Lexico Lexer = new Lexico(stringReader);
		parser sintactico = new parser(Lexer);
		TokenRulesNodeObject tokenRulesNodeObject = (TokenRulesNodeObject) sintactico.parse().value;
		try {
			FileWriter archivo = new FileWriter("arbol.dot");
			PrintWriter pw = new PrintWriter(archivo);
			String dotTree = tokenRulesNodeObject.nodeProgram().graph();
			String assemmlyString = tokenRulesNodeObject.nodeProgram().assembly();
			System.out.println(assemmlyString);
			pw.println(dotTree);
			archivo.close();

			File pngFile = new File("arbol.png");
			Graphviz.fromString(dotTree).render(Format.PNG).toFile(pngFile);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new TokenRulesObject(tokenRulesNodeObject.tokenList(), tokenRulesNodeObject.rulesList());
	}

}
