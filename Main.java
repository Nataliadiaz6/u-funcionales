import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Esta clase demuestra las operaciones terminales más comunes en Java Streams.
 * Las operaciones terminales son el paso final en un pipeline de Stream,
 * ya que producen un resultado o un efecto secundario (como una impresión).
 */
public class Main {

    /**
     * El método `main` es el punto de entrada principal del programa.
     * Aquí se ejecutan todos los ejemplos de operaciones terminales.
     *
     * @param args Argumentos de la línea de comandos (no se usan en este ejemplo).
     */
    public static void main(String[] args) {

        // Se crea una lista de nombres para los ejemplos 1, 2 y 3.
        List<String> nombres = List.of("Ana", "Carlos", "Elena", "David");

        // --- 1. Ejemplo de la operación terminal `count()` ---
        System.out.println("--- Ejemplo 1: count() ---");
        // `count()` devuelve el número total de elementos en el Stream como un `long`.
        long cantidad = nombres.stream().count();
        System.out.println("Total de nombres: " + cantidad); // Salida: 4
        System.out.println(); // Salto de línea

        // --- 2. Ejemplo de la operación terminal `forEach()` ---
        System.out.println("--- Ejemplo 2: forEach() ---");
        // `forEach()` ejecuta una acción para cada elemento del Stream.
        // No devuelve un valor, solo realiza el efecto secundario de la acción.
        System.out.println("Imprimiendo cada nombre:");
        nombres.stream().forEach(System.out::println);
        /* Salida:
           Ana
           Carlos
           Elena
           David
        */
        System.out.println();

        // --- 3. Ejemplo de la operación terminal `collect()` ---
        System.out.println("--- Ejemplo 3: collect() ---");
        // `collect()` recolecta los elementos del Stream en una colección o mapa.
        // Aquí, combinamos `map()` para transformar y `collect()` para recolectar.
        List<String> mayusculas = nombres.stream()
                // Primero, se transforma cada nombre a mayúsculas.
                .map(String::toUpperCase)
                // Luego, se recolectan los nombres transformados en una nueva lista.
                .collect(Collectors.toList());
        System.out.println("Lista de nombres en mayúsculas: " + mayusculas);
        System.out.println();

        // --- 4. Ejemplo de la operación terminal `reduce()` ---
        System.out.println("--- Ejemplo 4: reduce() ---");
        // `reduce()` combina los elementos de un Stream en un solo valor.
        List<Integer> numeros = List.of(2, 4, 6, 8);

        // Se usa `reduce()` para sumar todos los números de la lista.
        // El primer argumento (0) es el valor inicial (identidad).
        // El segundo argumento es un `BinaryOperator` que define la lógica de acumulación.
        int suma = numeros.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Suma total: " + suma); // Salida: 20
        System.out.println();

        // --- Ejemplo Integrador (Combinando varias operaciones) ---
        System.out.println("--- Ejemplo Integrador ---");

        // Se crea una lista de números para el ejemplo integrador.
        List<Integer> numerosIntegrador = List.of(2, 4, 6, 8, 3, 5, 7, 9);

        // Operación 1: Contar elementos
        // Se usa `count()` para obtener la cantidad total de números.
        long cantidadIntegrador = numerosIntegrador.stream().count();
        System.out.println("Total de números: " + cantidadIntegrador); // Salida: 8

        // Operación 2: Sumar todos los valores
        // Se usa `reduce()` para sumar los números. Se puede usar un método de referencia
        // para una sintaxis más concisa, como `Integer::sum`, que es equivalente a `(a, b) -> a + b`.
        int sumaIntegrador = numerosIntegrador.stream().reduce(0, Integer::sum);
        System.out.println("Suma total: " + sumaIntegrador); // Salida: 44

        // Operación 3: Agrupar por pares e impares
        // Se usa `collect()` con `Collectors.groupingBy()` para agrupar los números.
        // La expresión lambda `n -> n % 2 == 0 ? "Pares" : "Impares"` es el clasificador
        // que define el criterio de agrupación.
        Map<String, List<Integer>> agrupados = numerosIntegrador.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "Pares" : "Impares"));
        System.out.println("Números agrupados por paridad: " + agrupados);
        /* Salida:
           {Pares=[2, 4, 6, 8], Impares=[3, 5, 7, 9]}
        */
    }
}