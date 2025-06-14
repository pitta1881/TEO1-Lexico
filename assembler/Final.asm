include macros2.asm
include number.asm

.MODEL LARGE    ; tipo del modelo de memoria usado.
.386
.STACK  200h    ; bytes en el stack

.DATA           ; variables de la tabla de simbolos
;-------DATA SECTION-----------

.CODE           ; comienzo de la zona de codigo

    _start:
        MOV EAX,@DATA       ; inicializa el segmento de datos
        MOV DS,EAX
        MOV ES,EAX

;-------BODY SECTION-----------

        MOV EAX, 4C00h      ; termina la ejecucion.
        INT 21h
    END _start;