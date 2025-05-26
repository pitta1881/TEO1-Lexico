include macros2.asm
include number.asm

.MODEL LARGE    ; tipo del modelo de memoria usado.
.386
.STACK  200h    ; bytes en el stack

.DATA
;variables de la tabla de simbolos
mensaje DB 'Hola, mundo!$'   ; Cadena terminada en '$' para DOS
varFloatMult    DD          ?
varFloat    DD          ?
varInt      DD          ?
_25_5       DD          25.5
_0_0        DD          0.0
_10_0       DD          10.0
_50_0       DD          50.0
_1          DD          1
_0          DD          0



.CODE     ;comienzo de la zona de codigo
_start:
    MOV EAX,@DATA       ;inicializa el segmento de datos
    MOV DS,EAX
    MOV ES,EAX
;-------START REAL PROGRAM-----------

fld _25_5
fstp varFloat
ffree
While_2f017a7c: 
fld varFloat
fld _0_0
fxch 
fcom
fstsw ax
sahf
jbe EndWhile_2f017a7c
BlockBodyWhile_2f017a7c: 
fld varFloat
fld _10_0
fsub
fstp varFloat
ffree
displayFloat varFloat, 2
newLine 1
jmp While_2f017a7c
EndWhile_2f017a7c: 
ffree
fld _10_0
fstp varFloatMult
ffree
While_7b76102d: 
fld varFloatMult
fld _10_0
fxch 
fcom
fstsw ax
sahf
jb EndWhile_7b76102d
fld varFloatMult
fld _50_0
fxch 
fcom
fstsw ax
sahf
ja EndWhile_7b76102d
BlockBodyWhile_7b76102d: 
fld varFloatMult
fld _10_0
fadd
fstp varFloatMult
ffree
fld varFloatMult
fld _50_0
fxch 
fcom
fstsw ax
sahf
jne ElseIf_a472a6a
ThenIf_a472a6a: 
displayInteger _1
newLine 1
jmp EndIf_a472a6a
ElseIf_a472a6a: 
displayInteger _0
newLine 1
EndIf_a472a6a: 
ffree
displayFloat varFloatMult, 2
newLine 1
jmp While_7b76102d
EndWhile_7b76102d: 
ffree



;-------END REAL PROGRAM-----------
    MOV EAX, 4C00h  ; termina la ejecucion.
    INT 21h
END _start;