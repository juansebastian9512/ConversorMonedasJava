import java.util.Scanner;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConversorMonedas {
    
    // Tasas de cambio actualizadas (aproximadas)
    private static final BigDecimal USD_TO_COP = new BigDecimal("4200.0");  // Peso Colombiano
    private static final BigDecimal USD_TO_MXN = new BigDecimal("17.5");    // Peso Mexicano
    private static final BigDecimal USD_TO_BRL = new BigDecimal("5.2");     // Real Brasileño

    // Códigos ANSI para colores (Modo Oscuro)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BG = "\u001B[40m";
    public static final String ANSI_WHITE_TEXT = "\u001B[37m";

    private static boolean modoOscuro = false;
    
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat df = new DecimalFormat("#,##0.00");
    
    public static void main(String[] args) {
        System.out.println("=== CONVERSOR DE MONEDAS ===");
        System.out.println("Bienvenido al conversor de monedas");
        System.out.println();
        
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcionConversion();
            
            if (opcion == 0) {
                continuar = false;
                continue;
            } else if (opcion == 7) {
                modoOscuro = !modoOscuro;
                if (modoOscuro) {
                    System.out.print(ANSI_BLACK_BG + ANSI_WHITE_TEXT);
                    System.out.println("Modo oscuro activado.");
                } else {
                    System.out.print(ANSI_RESET);
                    System.out.println("Modo oscuro desactivado.");
                }
                continue;
            }
            
            BigDecimal cantidad = obtenerCantidad();
            BigDecimal resultado = realizarConversion(opcion, cantidad);
            mostrarResultado(opcion, cantidad, resultado);
            
            System.out.println();
            System.out.print("¿Desea realizar otra conversión? (s/n): ");
            String respuesta = scanner.nextLine().toLowerCase();
            continuar = respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");
            System.out.println();
        }
        
        if (modoOscuro) {
            System.out.print(ANSI_RESET);
        }
        System.out.println("¡Gracias por usar el conversor de monedas!");

        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("Seleccione el tipo de conversión:");
        System.out.println("1. USD → Peso Colombiano (COP)");
        System.out.println("2. Peso Colombiano (COP) → USD");
        System.out.println("3. USD → Peso Mexicano (MXN)");
        System.out.println("4. Peso Mexicano (MXN) → USD");
        System.out.println("5. USD → Real Brasileño (BRL)");
        System.out.println("6. Real Brasileño (BRL) → USD");
        System.out.println("7. " + (modoOscuro ? "Desactivar" : "Activar") + " Modo Oscuro");
        System.out.println("0. Salir");
        System.out.print("Ingrese su opción (0-7): ");
    }
    
    private static int obtenerOpcionConversion() {
        int opcion = -1;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                String input = scanner.nextLine();
                opcion = Integer.parseInt(input);
                
                if (opcion >= 0 && opcion <= 7) {
                    entradaValida = true;
                } else {
                    System.out.print("Opción inválida. Ingrese un número entre 0 y 7: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }
        
        return opcion;
    }
    
    private static BigDecimal obtenerCantidad() {
        BigDecimal cantidad = BigDecimal.ZERO;
        boolean entradaValida = false;
        
        System.out.print("Ingrese la cantidad a convertir: ");
        
        while (!entradaValida) {
            try {
                String input = scanner.nextLine();
                cantidad = new BigDecimal(input);
                
                if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
                    entradaValida = true;
                } else {
                    System.out.print("La cantidad debe ser mayor a 0. Ingrese nuevamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }
        
        return cantidad;
    }
    
    private static BigDecimal realizarConversion(int opcion, BigDecimal cantidad) {
        BigDecimal resultado = BigDecimal.ZERO;
        
        switch (opcion) {
            case 1: // USD → COP
                resultado = cantidad.multiply(USD_TO_COP);
                break;
            case 2: // COP → USD
                resultado = cantidad.divide(USD_TO_COP, 2, RoundingMode.HALF_UP);
                break;
            case 3: // USD → MXN
                resultado = cantidad.multiply(USD_TO_MXN);
                break;
            case 4: // MXN → USD
                resultado = cantidad.divide(USD_TO_MXN, 2, RoundingMode.HALF_UP);
                break;
            case 5: // USD → BRL
                resultado = cantidad.multiply(USD_TO_BRL);
                break;
            case 6: // BRL → USD
                resultado = cantidad.divide(USD_TO_BRL, 2, RoundingMode.HALF_UP);
                break;
        }
        
        return resultado;
    }
    
    private static void mostrarResultado(int opcion, BigDecimal cantidad, BigDecimal resultado) {
        String monedaOrigen = "";
        String monedaDestino = "";
        String simboloOrigen = "";
        String simboloDestino = "";
        
        switch (opcion) {
            case 1:
                monedaOrigen = "Dólares Estadounidenses";
                monedaDestino = "Pesos Colombianos";
                simboloOrigen = "USD";
                simboloDestino = "COP";
                break;
            case 2:
                monedaOrigen = "Pesos Colombianos";
                monedaDestino = "Dólares Estadounidenses";
                simboloOrigen = "COP";
                simboloDestino = "USD";
                break;
            case 3:
                monedaOrigen = "Dólares Estadounidenses";
                monedaDestino = "Pesos Mexicanos";
                simboloOrigen = "USD";
                simboloDestino = "MXN";
                break;
            case 4:
                monedaOrigen = "Pesos Mexicanos";
                monedaDestino = "Dólares Estadounidenses";
                simboloOrigen = "MXN";
                simboloDestino = "USD";
                break;
            case 5:
                monedaOrigen = "Dólares Estadounidenses";
                monedaDestino = "Reales Brasileños";
                simboloOrigen = "USD";
                simboloDestino = "BRL";
                break;
            case 6:
                monedaOrigen = "Reales Brasileños";
                monedaDestino = "Dólares Estadounidenses";
                simboloOrigen = "BRL";
                simboloDestino = "USD";
                break;
        }
        
        System.out.println();
        System.out.println("=== RESULTADO DE LA CONVERSIÓN ===");
        System.out.println("Cantidad original: " + df.format(cantidad) + " " + simboloOrigen + " (" + monedaOrigen + ")");
        System.out.println("Cantidad convertida: " + df.format(resultado) + " " + simboloDestino + " (" + monedaDestino + ")");
        System.out.println("===================================");
    }
}
