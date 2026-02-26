# Calculadora con ANTLR en Java

## Descripción

Este proyecto implementa la calculadora del Capítulo 4 utilizando ANTLR como generador de parser y Java como lenguaje objetivo.

El repositorio incluye:

- Ejemplo básico de prueba (Hello.g4) para verificar instalación
- Implementación completa de la calculadora
- Archivos necesarios para compilar y ejecutar
- Evidencia gráfica del funcionamiento

---

#  1. Verificación de instalación de ANTLR

Antes de implementar la calculadora, se realizó una prueba básica utilizando el ejemplo `Hello.g4`.

## Archivo utilizado
Hello.g4

```antlr
grammar Hello;

r : 'hello' ID ;
ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;
```

## Proceso ejecutado
1. Generar parser
antlr4 Hello.g4

2. Compilacion
javac *.java

3. Ejecucion
grun Hello r -tree
grun Hello r -gui

- <img width="948" height="491" alt="image" src="https://github.com/user-attachments/assets/df59a8f6-f976-4658-b3d9-aadf9aa3c0d4" />
- <img width="397" height="138" alt="image" src="https://github.com/user-attachments/assets/92b78b3b-79af-410c-943a-eb298505eb88" />
- <img width="601" height="498" alt="image" src="https://github.com/user-attachments/assets/cda0f6d7-2ab7-4c82-a13f-01d5ebe00826" />

- ANTLR genera automáticamente el Lexer y Parser
- javac compila los archivos generados
- grun permite probar la gramática
- El árbol muestra que la regla r reconoce correctamente la cadena "hello angel"

## Implementacion Calculadora CAP4
Se implementó una calculadora que permite:

- Suma (+)
- Resta (-)
- Multiplicación (*)
- División (/)
- Manejo de variables
- Evaluación de expresiones

## Archivos del proyecto

Los siguientes archivos deben estar en la misma carpeta:
- LabeledExpr.g4 → Gramática
- EvalVisitor.java → Lógica de evaluación
- Calc.java → Programa principal
- antlr-4.13.2-complete.jar

Los siguientes archivos se generan automáticamente:
- LabeledExprLexer.java
- LabeledExprParser.java
- LabeledExprBaseVisitor.java
- LabeledExprVisitor.java

## Proceso Implementacion
- Generar Archivos de ANTLR:
antlr4 -no-listener -visitor LabeledExpr.g4 (Se usa -visitor porque la evaluación se implementa con patrón Visitor)
-<img width="539" height="93" alt="image" src="https://github.com/user-attachments/assets/bb7e5995-6124-4459-b769-6c00cb5f3fd4" />

- Compilar todo
javac *.java

- Ejecutar Calculadora
java Calc t.expr (Donde t.expr contiene las expresiones a evaluar.)

## Funcionamiento 
- LabeledExpr.g4 define reglas sintácticas para expresiones matemáticas.
- EvalVisitor.java implementa la lógica para:
- Evaluar operaciones
- Manejar memoria de variables
- Mostrar errores (por ejemplo: variable no definida)
- Calc.java inicia el proceso de parsing y evaluación.
<img width="644" height="287" alt="image" src="https://github.com/user-attachments/assets/ef058d0c-cc2d-4ac1-b4a5-716ff045972d" />
- Correcta evaluación de operaciones
- Manejo de errores semánticos
- Funcionamiento completo del intérprete

## Ejecucion
- Descargar el repositorio
- Abrir terminal en la carpeta
- Ejecutar:
- java -jar antlr-4.13.2-complete.jar -no-listener -visitor LabeledExpr.g4
- javac *.java
- java Calc t.expr


