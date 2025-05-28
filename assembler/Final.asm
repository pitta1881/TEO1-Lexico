include macros2.asm
include number.asm

.MODEL LARGE    ; tipo del modelo de memoria usado.
.386
.STACK  200h    ; bytes en el stack

.DATA
;variables de la tabla de simbolos
varFloatMul DD          ?
varIntMul   DD          ?
varFloatElse    DD          ?
varFloatComp    DD          ?
varIntComp  DD          ?
varCompuest DD          ?
varFloat2   DD          ?
varInt2     DD          ?
varFloatMult    DD          ?
varFloat    DD          ?
varInt      DD          ?
found       DD          ?
searchVal   DD          ?
i           DD          ?
fibNext     DD          ?
fib2        DD          ?
fib1        DD          ?
n           DD          ?
factorial   DD          ?
passMark    DD          ?
avg         DD          ?
count       DD          ?
sum         DD          ?
_1          DD          1
_5          DD          5
message_330bbc4 DB          'Factorial of 5:$'
_0          DD          0
message_5a5e42fd    DB          'Fibonacci sequence:$'
_3          DD          3
_8          DD          8
_7          DD          7
_10         DD          10
message_79b1975b    DB          'Value 7 found in range 1 to 10$'
message_7fd9ed32    DB          'Value 7 not found$'


.CODE     ;comienzo de la zona de codigo
_start:
    MOV EAX,@DATA       ;inicializa el segmento de datos
    MOV DS,EAX
    MOV ES,EAX
;-------START REAL PROGRAM-----------

fild _1
fistp factorial
ffree
fild _5
fistp n
ffree
fild _1
fistp count
ffree
While_66c23f3f: 
fld count
fld n
fxch 
fcom
fstsw ax
sahf
ja EndWhile_66c23f3f
BlockBodyWhile_66c23f3f: 
fild factorial
fild count
fmul
fistp factorial
ffree
fild count
fild _1
fadd
fistp count
ffree
jmp While_66c23f3f
EndWhile_66c23f3f: 
ffree
displayString message_330bbc4
newLine 1
displayInteger factorial
newLine 1
fild _0
fistp fib1
ffree
fild _1
fistp fib2
ffree
displayString message_5a5e42fd
newLine 1
displayInteger fib1
newLine 1
displayInteger fib2
newLine 1
fild _3
fistp i
ffree
While_40eb77bb: 
fld i
fld _8
fxch 
fcom
fstsw ax
sahf
ja EndWhile_40eb77bb
BlockBodyWhile_40eb77bb: 
fild fib1
fild fib2
fadd
fistp fibNext
ffree
displayInteger fibNext
newLine 1
fild fib2
fistp fib1
ffree
fild fibNext
fistp fib2
ffree
fild i
fild _1
fadd
fistp i
ffree
jmp While_40eb77bb
EndWhile_40eb77bb: 
ffree
fild _7
fistp searchVal
ffree
fild _0
fistp found
ffree
fild _1
fistp count
ffree
While_11a66ae1: 
fld count
fld _10
fxch 
fcom
fstsw ax
sahf
ja EndWhile_11a66ae1
BlockBodyWhile_11a66ae1: 
fld count
fld searchVal
fxch 
fcom
fstsw ax
sahf
jne EndIf_34508194
ThenIf_34508194: 
fild _1
fistp found
ffree
jmp EndIf_34508194
EndIf_34508194: 
ffree
fild count
fild _1
fadd
fistp count
ffree
jmp While_11a66ae1
EndWhile_11a66ae1: 
ffree
fld found
fld _1
fxch 
fcom
fstsw ax
sahf
jne ElseIf_3f3667f
ThenIf_3f3667f: 
displayString message_79b1975b
newLine 1
jmp EndIf_3f3667f
ElseIf_3f3667f: 
displayString message_7fd9ed32
newLine 1
EndIf_3f3667f: 
ffree



;-------END REAL PROGRAM-----------
    MOV EAX, 4C00h  ; termina la ejecucion.
    INT 21h
END _start;