package com.Lexico.FlexLexico;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.io.File;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

public class FlexLexico {
	private static String dataSectionASM = null;
	private static String bodySectionASM = null;

	public static TokenRulesObject analizar(String inputText) throws Exception {
		StringReader stringReader = new StringReader(inputText);
		Lexico Lexer = new Lexico(stringReader);
		parser sintactico = new parser(Lexer);
		TokenRulesNodeObject tokenRulesNodeObject = (TokenRulesNodeObject) sintactico.parse().value;
		try {
			FileWriter archivo = new FileWriter("arbol.dot");
			PrintWriter pw = new PrintWriter(archivo);
			String dotTree = tokenRulesNodeObject.nodeProgram().graph();
			bodySectionASM = tokenRulesNodeObject.nodeProgram().assembly();
			pw.println(dotTree);
			archivo.close();

			File pngFile = new File("arbol.png");
			Graphviz.fromString(dotTree).render(Format.PNG).toFile(pngFile);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new TokenRulesObject(tokenRulesNodeObject.tokenList(), tokenRulesNodeObject.rulesList());
	}

	public static void setDataSectionASM(String dataSection) {
		dataSectionASM = dataSection;
	}

	public static void updateFinalAsmWithSections() throws Exception {
		Path templatePath = Paths.get("assembler/Template.asm");
		Path finalPath = Paths.get("assembler/Final.asm");
		Files.copy(templatePath, finalPath, StandardCopyOption.REPLACE_EXISTING);

		String dataMarker = ";-------DATA SECTION-----------";
		String bodyMarker = ";-------BODY SECTION-----------";
		List<String> lines = Files.readAllLines(finalPath);
		List<String> newLines = new java.util.ArrayList<>(lines.size() + 2);
		boolean dataInserted = false;
		boolean bodyInserted = false;

		for (String line : lines) {
			newLines.add(line);
			if (!dataInserted && line.contains(dataMarker)) {
				if (dataSectionASM != null && !dataSectionASM.isEmpty()) {
					newLines.add(dataSectionASM);
				}
				dataInserted = true;
			}
			if (!bodyInserted && line.contains(bodyMarker)) {
				if (bodySectionASM != null && !bodySectionASM.isEmpty()) {
					newLines.add(bodySectionASM);
				}
				bodyInserted = true;
			}
		}

		Files.write(finalPath, newLines);
	}

}
