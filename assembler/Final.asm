include macros2.asm
include number.asm

.MODEL LARGE    ; tipo del modelo de memoria usado.
.386
.STACK  200h    ; bytes en el stack

.DATA
;variables de la tabla de simbolos
mensaje DB 'Hola, mundo!$'   ; Cadena terminada en '$' para DOS
varFloat    DD          ?
varInt      DD          ?
varFloat2   DD          ?
varInt2     DD          ?
varComp     DD          ?
_2          DD          2
_3          DD          3
_3_4        DD          3.4
_1_6        DD          1.6
_7          DD          7
_6_5        DD          6.5
_2_1        DD          2.1
_37_0       DD          37.0
_3_0        DD          3.0
_5_0        DD          5.0


.CODE     ;comienzo de la zona de codigo
_start:
    MOV EAX,@DATA       ;inicializa el segmento de datos
    MOV DS,EAX
    MOV ES,EAX
;-------START REAL PROGRAM-----------

fild _2
fild _3
fmul
fstp varInt
ffree
fld _3_4
fld _1_6
fadd
fstp varFloat
ffree
displayFloat varInt, 2
newLine 1
displayFloat varFloat, 2
newLine 1
fild _7
fild _3
fdiv
fstp varInt2
ffree
fld _6_5
fld _2_1
fsub
fstp varFloat2
ffree
displayFloat varInt2, 2
newLine 1
displayFloat varFloat2, 2
newLine 1
fld _37_0
fld _3_0
fadd
fld varFloat2
fsub
fld _5_0
fdiv
fstp varComp
ffree
displayFloat varComp, 2
newLine 1

;-------END REAL PROGRAM-----------
    MOV EAX, 4C00h  ; termina la ejecucion.
    INT 21h
END _start;
;