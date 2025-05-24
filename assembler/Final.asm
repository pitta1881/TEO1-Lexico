include macros2.asm
include number.asm

.MODEL LARGE    ; tipo del modelo de memoria usado.
.386
.STACK  200h    ; bytes en el stack

.DATA
;variables de la tabla de simbolos
mensaje DB 'Hola, mundo!$'   ; Cadena terminada en '$' para DOS

.CODE     ;comienzo de la zona de codigo
_start:
    MOV EAX,@DATA       ;inicializa el segmento de datos
    MOV DS,EAX
    MOV ES,EAX
;-------START REAL PROGRAM-----------

    ; Mostrar el mensaje
    displayString mensaje

;-------END REAL PROGRAM-----------
    MOV EAX, 4C00h  ; termina la ejecucion.
    INT 21h
END _start;
;