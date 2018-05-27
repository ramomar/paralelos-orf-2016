# Paralelos ORF 2016

![Tiempo vs Número de hilos](https://user-images.githubusercontent.com/10622989/40581609-db5d027a-6122-11e8-84b6-f3f96388659a.png)

Este fue el proyecto de medio curso para la clase de Sistemas Paralelos y Distribuidos.
La finalidad de este proyecto era experimentar con las APIs de Java para computación paralela.

Elegí leer secuencias de ADN que codifican proteinas, pues estas se pueden leer de 6 maneras diferentes (solo implemente lectura hacia la derecha porque tanto detalle no era necesario), y mi idea era hacer fuerza bruta para ver
que no siempre ayuda tener más hilos.

### Contexto
_El Ácido Desoxirribonucleico (ADN), es una molécula de gran tamaño que guarda y transmite de generación en generación toda la información necesaria para el desarrollo de todas las funciones biológicas de un organismo. Este esta formado por la unión paralela de dos cadenas; cada cadena se encuentra conformada por cuatro nucleótidos diferentes.
Estos cuatro nucleótidos se denominan Adenina (A), Timina (T), Guanina (G) y Citosina (C)._
 
_El ADN se puede dividir en codones que son secuencias de tres nucleótidos que indican a la célula en donde comienza el “código” para sintetizar proteínas que posteriormente se usarán para soportar los procesos biológicos de la célula._

_La información que se interpreta del ADN depende desde donde se empiece la lectura; a las posiciones en donde se empieza a leer el ADN se les denomina marcos abiertos de lectura (ORF).
Un ORF es una secuencia de ADN que va desde un cordón de inicio hasta un cordón de terminación._

_Una secuencia de ADN puede ser leída en seis posiciones de marco de lectura. Tres pasos para adelante y tres pasos en dirección contraria, esto es debido a que cada codón está compuesto por tres nucleótidos y el código debe ser leído de tres en tres. 
Según el marco que se escoja es una interpretación diferente._

_El proyecto consiste en analizar una parte del genoma de una mosca de fruta de manera paralela basándose en datos abiertos conseguidos de internet para determinar los aminoácidos que codifican proteínas. Los datos están en formato FASTA, por lo que será necesario implementar clases con funcionalidades rudimentarias para extraer la información de dichos datos y procesesarla._

Un diagrama de flujo sencillo que explica la funcionalidad del proyecto:

<p align="center">
<img width="414" alt="screen shot 2018-05-26 at 8 20 52 pm" src="https://user-images.githubusercontent.com/10622989/40581610-e2fc486a-6122-11e8-8b5e-cd39b5f95067.png">
</p>
