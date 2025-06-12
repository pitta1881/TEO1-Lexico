# FlexLexico

## Overview

FlexLexico is a Java-based lexical analyzer project. It reads and processes a file named `prueba.txt` to perform lexical analysis. The results are saved in a file named `ts.txt`.

## Project Structure

- **src/main/java/com/Lexico/FlexLexico**: Contains the main Java classes.
- **prueba.txt**: The input file for the lexical analyzer.
- **ts.txt**: The output file containing the token names and values.
- **pom.xml**: Maven configuration file.

## Prerequisites

- Java 21
- Maven
- java-cup-11b (version 2015.03.26)

## Setup

1. Clone the repository:

   ```sh
   git clone https://github.com/pitta1881/TEO1-Lexico.git
   cd FlexLexico
   ```

2. Build the project using Maven:
   ```sh
   mvn clean install
   ```

## Build the JAR

To build the .jar file, use the following Maven command:

```sh
mvn clean package
```

The resulting .jar file will be located in the target directory

## Run the JAR

To run the .jar file, use the following command:

```sh
java -jar .\FlexLexico.jar
```

## Usage

The UI will allow you to load the input file or write in the text area and display the results. The tokens will be saved in ts.txt.

## Assembly

To create the **Final.asm**, watch _DemoAssembler.mkv_ or follow this steps:

1. Run **FlexLexico.jar**
2. Write and compile a program (or choose some prueba\*.txt)
3. Click 'Analizar' button
4. Copy the new content in _asmBody.txt_ and paste it in _assembler/Final.asm_, just after comment: **;-------START REAL PROGRAM-----------**
5. Copy the new content in _ts.txt_ and paste it in _assembler/Final.asm_, just after section: **.DATA**
6. Assemble, build and execute assembly program

## Anexo

[Link Documento REGEX](https://docs.google.com/document/d/1_P1gy3LVajhrFVl_u1IOgZa34uSg5c2fvrE33nPvs7E/edit?usp=sharing)  
[Link Documento GLC](https://docs.google.com/document/d/1ycB5KUmSl6u1RavsXuVSEvb_l2KPiQhOIwfzFiT8jUQ/edit?usp=sharing)

## Utils

```sh
java -jar .\java-cup-11b-2015.03.26.jar -destdir .\TEOFlex\src\main\java\com\Lexico\FlexLexico\ .\TEOFlex\Sintactico.cup
java -jar .\jflex-full-1.9.1.jar .\TEOFlex\Lexico.flex -d .\TEOFlex\src\main\java\com\Lexico\FlexLexico\
```
