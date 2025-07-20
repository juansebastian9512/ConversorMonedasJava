# Conversor de Monedas en Java

## Descripción
Este es un conversor de monedas desarrollado en Java que permite convertir entre las siguientes monedas:
- Dólar Estadounidense (USD)
- Peso Colombiano (COP)
- Peso Mexicano (MXN)
- Real Brasileño (BRL)

## Funcionalidades
- Conversión bidireccional entre USD y las demás monedas
- Interfaz de usuario interactiva por consola
- Validación de entrada de datos
- Formato de números con separadores de miles
- Opción de realizar múltiples conversiones

## Conversiones Disponibles
1. USD → Peso Colombiano (COP)
2. Peso Colombiano (COP) → USD
3. USD → Peso Mexicano (MXN)
4. Peso Mexicano (MXN) → USD
5. USD → Real Brasileño (BRL)
6. Real Brasileño (BRL) → USD

## Tasas de Cambio
Las tasas de cambio utilizadas son aproximadas y están definidas en el código:
- 1 USD = 4,200 COP
- 1 USD = 17.5 MXN
- 1 USD = 5.2 BRL

## Cómo usar
1. Ejecutar el programa
2. Seleccionar el tipo de conversión deseada (1-6)
3. Ingresar la cantidad a convertir
4. Ver el resultado de la conversión
5. Opción de realizar otra conversión o salir

## Compilación y Ejecución
```bash
# Compilar
javac ConversorMonedas.java

# Ejecutar
java ConversorMonedas
```

## Características del Código
- Manejo de excepciones para entradas inválidas
- Validación de datos de entrada
- Código modular con métodos separados
- Interfaz de usuario clara y amigable
- Formato de números para mejor legibilidad
