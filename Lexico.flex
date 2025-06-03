package com.Lexico.FlexLexico;

import java_cup.runtime.Symbol;
import com.Lexico.FlexLexico.sym;
%%

%cup
%public
%class Lexico
%line
%column
%char
%unicode

DIGITO = [0-9]
LETRA_MINUSCULA = [a-zñ]
LETRA_MAYUSCULA = [A-ZÑ]
LETRA = {LETRA_MINUSCULA}|{LETRA_MAYUSCULA}
ESPACIO = [ \t\n\r\f]+
CONST_STRING_ERROR = "'"([^'\n])"'"
CONST_STRING = \"([^\"\n])*\"
CONST_NUM_REAL = {DIGITO}*"."{DIGITO}+|{DIGITO}+"."{DIGITO}*
CONST_NUM_INT = {DIGITO}+
CONST_BASE_BIN = "0b"(0|1)+
ID_ERROR = _({LETRA}|{DIGITO}|_)*
ID = {LETRA_MINUSCULA}({LETRA}|{DIGITO}|_)*
COMENTARIOS = "//*"([^*]|\*+[^/])*"\*//"

%%

<YYINITIAL> {
    "WHILE"                 { return new Symbol(sym.While, yyline + 1, yycolumn + 1, yytext()); }
    "IF"                    { return new Symbol(sym.If, yyline + 1, yycolumn + 1, yytext()); }
    "THEN"                  { return new Symbol(sym.Then, yyline + 1, yycolumn + 1, yytext()); }
    "ELSE"                  { return new Symbol(sym.Else, yyline + 1, yycolumn + 1, yytext()); }
    "ENDIF"                 { return new Symbol(sym.Endif, yyline + 1, yycolumn + 1, yytext()); }
    "NOT"                   { return new Symbol(sym.Not, yyline + 1, yycolumn + 1, yytext()); }
    "AND"                   { return new Symbol(sym.And, yyline + 1, yycolumn + 1, yytext()); }
    "OR"                    { return new Symbol(sym.Or, yyline + 1, yycolumn + 1, yytext()); }
    "DECLARE.SECTION"       { return new Symbol(sym.Declare_section, yyline + 1, yycolumn + 1, yytext()); }
    "ENDDECLARE.SECTION"    { return new Symbol(sym.Enddeclare_section, yyline + 1, yycolumn + 1, yytext()); }
    "PROGRAM.SECTION"       { return new Symbol(sym.Program_section, yyline + 1, yycolumn + 1, yytext()); }
    "ENDPROGRAM.SECTION"    { return new Symbol(sym.Endprogram_section, yyline + 1, yycolumn + 1, yytext()); }
    "AsigComp"              { return new Symbol(sym.Asign_comp, yyline + 1, yycolumn + 1, yytext()); }
    "FLOAT"                 { return new Symbol(sym.Float, yyline + 1, yycolumn + 1, yytext()); }
    "INT"                   { return new Symbol(sym.Int, yyline + 1, yycolumn + 1, yytext()); }
    "STRING"                { return new Symbol(sym.String, yyline + 1, yycolumn + 1, yytext()); }
    "WRITE"                 { return new Symbol(sym.Write, yyline + 1, yycolumn + 1, yytext()); }

    "{"                     { return new Symbol(sym.Llave_abierta, yyline + 1, yycolumn + 1, yytext()); }
    "}"                     { return new Symbol(sym.Llave_cerrada, yyline + 1, yycolumn + 1, yytext()); }
    "("                     { return new Symbol(sym.Parentesis_abierto, yyline + 1, yycolumn + 1, yytext()); }
    ")"                     { return new Symbol(sym.Parentesis_cerrado, yyline + 1, yycolumn + 1, yytext()); }
    "["                     { return new Symbol(sym.Corchete_abierto, yyline + 1, yycolumn + 1, yytext()); }
    "]"                     { return new Symbol(sym.Corchete_cerrado, yyline + 1, yycolumn + 1, yytext()); }
    "="                     { return new Symbol(sym.Asignacion, yyline + 1, yycolumn + 1, yytext()); }
    "<"                     { return new Symbol(sym.Menor_que, yyline + 1, yycolumn + 1, yytext()); }
    ">"                     { return new Symbol(sym.Mayor_que, yyline + 1, yycolumn + 1, yytext()); }
    ">="                    { return new Symbol(sym.Mayor_o_igual_que, yyline + 1, yycolumn + 1, yytext()); }
    "<="                    { return new Symbol(sym.Menor_o_igual_que, yyline + 1, yycolumn + 1, yytext()); }
    "=="                    { return new Symbol(sym.Igual, yyline + 1, yycolumn + 1, yytext()); }
    "!="                    { return new Symbol(sym.Diferente, yyline + 1, yycolumn + 1, yytext()); }
    ":="                    { return new Symbol(sym.Asign_mult, yyline + 1, yycolumn + 1, yytext()); }
    ";"                     { return new Symbol(sym.Punto_y_coma, yyline + 1, yycolumn + 1, yytext()); }
    ":"                     { return new Symbol(sym.Dos_puntos, yyline + 1, yycolumn + 1, yytext()); }
    ","                     { return new Symbol(sym.Coma, yyline + 1, yycolumn + 1, yytext()); }
    "+"                     { return new Symbol(sym.Suma, yyline + 1, yycolumn + 1, yytext()); }
    "-"                     { return new Symbol(sym.Resta, yyline + 1, yycolumn + 1, yytext()); }
    "*"                     { return new Symbol(sym.Multiplicacion, yyline + 1, yycolumn + 1, yytext()); }
    "/"                     { return new Symbol(sym.Division, yyline + 1, yycolumn + 1, yytext()); }
    {CONST_STRING} {
        if (yytext().length() - 2 <= 30) { // Verifica el límite de 30 caracteres
            return new Symbol(sym.Cte_s, yyline + 1, yycolumn + 1, yytext());
        } else {
            return new Symbol(1003, yyline + 1, yycolumn + 1, yytext());
        }
    }
    {CONST_NUM_REAL} {
        // if cast to Float fail, return error
        try {
            Float.parseFloat(yytext());
            return new Symbol(sym.Cte_f, yyline + 1, yycolumn + 1, yytext());
        } catch (NumberFormatException e) {
            return new Symbol(1004, yyline + 1, yycolumn + 1, yytext());
        }
     }
    {CONST_NUM_INT} {
     // if cast to Integer of 16bits fail, return error
        try {
            int num = Integer.parseInt(yytext());
            if (num >= -32768 && num <= 32767) {
                return new Symbol(sym.Cte_i, yyline + 1, yycolumn + 1, yytext());
            } else {
                return new Symbol(1005, yyline + 1, yycolumn + 1, yytext());
            }
        } catch (NumberFormatException e) {
            return new Symbol(1005, yyline + 1, yycolumn + 1, yytext());
        }
    }
    {CONST_BASE_BIN}        { return new Symbol(sym.Cte_b, yyline + 1, yycolumn + 1, yytext()); }
    {ID}                    { return new Symbol(sym.Id, yyline + 1, yycolumn + 1, yytext()); }
    {COMENTARIOS}           { }
    {ESPACIO}               { }
    {CONST_STRING_ERROR}    { return new Symbol(1001, yyline + 1, yycolumn + 1, yytext()); }
    {ID_ERROR}           	{ return new Symbol(1002, yyline + 1, yycolumn + 1, yytext()); }
}
[^]                         { return new Symbol(1000, yyline + 1, yycolumn + 1, yytext()); }