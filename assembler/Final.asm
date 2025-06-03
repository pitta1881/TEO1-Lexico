include macros2.asm
include number.asm

.MODEL LARGE    ; tipo del modelo de memoria usado.
.386
.STACK  200h    ; bytes en el stack

.DATA
;variables de la tabla de simbolos
zeroFloat   DD          ?
zeroInt     DD          ?
countFloat  DD          ?
countInt    DD          ?
sumFloat    DD          ?
sumInt      DD          ?
passMark    DD          ?
avg         DD          ?
n           DD          ?
factorial   DD          ?
i           DD          ?
fibNext     DD          ?
fib2        DD          ?
fib1        DD          ?
found       DD          ?
searchVal   DD          ?
_0          DD          0
_0_0        DD          0.0
_1          DD          1
_10         DD          10
message_6787c611    DB          'Sum of 1 to 10:$'
_1_0        DD          1.0
_5_0        DD          5.0
_2_0        DD          2.0
message_59aeb61b    DB          'Average is:$'
_6_0        DD          6.0
message_330bbc4 DB          'Status: PASSED$'
message_5a5e42fd    DB          'Status: FAILED$'
_5          DD          5
message_79b1975b    DB          'Factorial of 5:$'
message_7fd9ed32    DB          'Fibonacci sequence:$'
_3          DD          3
_8          DD          8
_7          DD          7
message_57b8119e    DB          'Value 7 found in range 1 to 10$'
message_1d9d696a    DB          'Value 7 not found$'



.CODE     ;comienzo de la zona de codigo
_start:
    MOV EAX,@DATA       ;inicializa el segmento de datos
    MOV DS,EAX
    MOV ES,EAX
;-------START REAL PROGRAM-----------

fild _0
fistp zeroInt
ffree
fld _0_0
fstp zeroFloat
ffree
fild zeroInt
fistp sumInt
ffree
fild _1
fistp countInt
ffree
While_5e13aa9: 
fld countInt
fld _10
fxch 
fcom
fstsw ax
sahf
ja EndWhile_5e13aa9
BlockBodyWhile_5e13aa9: 
fild sumInt
fild countInt
fadd
fistp sumInt
ffree
fild countInt
fild _1
fadd
fistp countInt
ffree
jmp While_5e13aa9
EndWhile_5e13aa9: 
ffree
displayString message_6787c611
newLine 1
displayInteger sumInt
newLine 1
fld zeroFloat
fstp sumFloat
ffree
fld _1_0
fstp countFloat
ffree
While_2248b2e3: 
fld countFloat
fld _5_0
fxch 
fcom
fstsw ax
sahf
ja EndWhile_2248b2e3
BlockBodyWhile_2248b2e3: 
fld sumFloat
fld countFloat
fld _2_0
fmul
fadd
fstp sumFloat
ffree
fld countFloat
fld _1_0
fadd
fstp countFloat
ffree
jmp While_2248b2e3
EndWhile_2248b2e3: 
ffree
fld sumFloat
fld _5_0
fdiv
fstp avg
ffree
displayString message_59aeb61b
newLine 1
displayFloat avg, 2
newLine 1
fld _6_0
fstp passMark
ffree
fld avg
fld passMark
fxch 
fcom
fstsw ax
sahf
jb ElseIf_3af27889
ThenIf_3af27889: 
displayString message_330bbc4
newLine 1
jmp EndIf_3af27889
ElseIf_3af27889: 
displayString message_5a5e42fd
newLine 1
EndIf_3af27889: 
ffree
fild _1
fistp factorial
ffree
fild _5
fistp n
ffree
fild _1
fistp countInt
ffree
While_646814b: 
fld countInt
fld n
fxch 
fcom
fstsw ax
sahf
ja EndWhile_646814b
BlockBodyWhile_646814b: 
fild factorial
fild countInt
fmul
fistp factorial
ffree
fild countInt
fild _1
fadd
fistp countInt
ffree
jmp While_646814b
EndWhile_646814b: 
ffree
displayString message_79b1975b
newLine 1
displayInteger factorial
newLine 1
fild _0
fistp fib1
ffree
fild _1
fistp fib2
ffree
displayString message_7fd9ed32
newLine 1
displayInteger fib1
newLine 1
displayInteger fib2
newLine 1
fild _3
fistp i
ffree
While_591ab8f5: 
fld i
fld _8
fxch 
fcom
fstsw ax
sahf
ja EndWhile_591ab8f5
BlockBodyWhile_591ab8f5: 
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
jmp While_591ab8f5
EndWhile_591ab8f5: 
ffree
fild _7
fistp searchVal
ffree
fild _0
fistp found
ffree
fild _1
fistp countInt
ffree
While_6f5f4132: 
fld countInt
fld _10
fxch 
fcom
fstsw ax
sahf
ja EndWhile_6f5f4132
BlockBodyWhile_6f5f4132: 
fld countInt
fld searchVal
fxch 
fcom
fstsw ax
sahf
jne EndIf_2b40132
ThenIf_2b40132: 
fild _1
fistp found
ffree
jmp EndIf_2b40132
EndIf_2b40132: 
ffree
fild countInt
fild _1
fadd
fistp countInt
ffree
jmp While_6f5f4132
EndWhile_6f5f4132: 
ffree
fld found
fld _1
fxch 
fcom
fstsw ax
sahf
jne ElseIf_5aac8feb
ThenIf_5aac8feb: 
displayString message_57b8119e
newLine 1
jmp EndIf_5aac8feb
ElseIf_5aac8feb: 
displayString message_1d9d696a
newLine 1
EndIf_5aac8feb: 
ffree


;-------END REAL PROGRAM-----------
    MOV EAX, 4C00h  ; termina la ejecucion.
    INT 21h
END _start;