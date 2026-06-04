import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Esta clase demuestra el uso de algunas de las operaciones más comunes
 * en Java Streams.
 *
 * Los Streams son una poderosa API de Java 8 para procesar secuencias
 * de elementos de forma funcional, permitiendo operaciones
 * como filtrado, mapeo y ordenación de manera declarativa y concisa.
 */
public class Main {

    /**
     * El método `main` es el punto de entrada principal del programa.
     * Aquí se ejecutan todos los ejemplos de Streams.
     *
     * @param args Argumentos de la línea de comandos (no se usan en este ejemplo).
     */
    public static void main(String[] args) {

        // --- 1. Ejemplo de la operación `filter` ---
        System.out.println("--- Ejemplo 1: filter (filtrar) ---");
        // Se crea una lista de números.
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Se crea un Stream a partir de la lista.
        numeros.stream()
                // La operación `filter` toma un `Predicate` y mantiene solo los
                // elementos que cumplen con la condición. En este caso,
                // filtra los números que son pares (n % 2 == 0).
                .filter(n -> n % 2 == 0)
                // Se usa `forEach` para imprimir cada elemento del Stream resultante.
                .forEach(System.out::println); // Salida: 2, 4, 6, 8, 10
        System.out.println();

        // --- 2. Ejemplo de la operación `map` ---
        System.out.println("--- Ejemplo 2: map (mapear/transformar) ---");

        // Ejemplo A: Convertir a mayúsculas
        // Se crea una lista de nombres.
        List<String> nombres = List.of("ana", "juan", "elena");
        // El Stream transforma cada nombre.
        nombres.stream()
                // La operación `map` toma una `Function` y aplica una
                // transformación a cada elemento. Aquí, convierte cada
                // cadena a mayúsculas.
                .map(String::toUpperCase)
                .forEach(System.out::println); // Salida: ANA, JUAN, ELENA
        System.out.println(); // Salto de línea

        // Ejemplo B: Obtener la longitud de cada palabra
        // El Stream transforma cada nombre en su longitud (un entero).
        nombres.stream()
                // Aquí, el `map` transforma cada `String` a un `Integer`.
                .map(String::length)
                .forEach(System.out::println); // Salida: 3, 4, 5
        System.out.println();

        // --- 3. Ejemplo de la operación `distinct` ---
        System.out.println("--- Ejemplo 3: distinct (elementos únicos) ---");
        // Se crea una lista con elementos duplicados.
        List<String> frutas = List.of("manzana", "naranja", "manzana", "pera", "naranja");
        frutas.stream()
                // La operación `distinct` elimina los elementos duplicados
                // del Stream, basándose en el método `equals()`.
                .distinct()
                .forEach(System.out::println); // Salida: manzana, naranja, pera
        System.out.println();

        // --- 4. Ejemplo de la operación `sorted` ---
        System.out.println("--- Ejemplo 4: sorted (ordenar) ---");

        // Ejemplo A: Ordenar números
        // Se crea una lista de números desordenados.
        List<Integer> numerosSorted = List.of(5, 2, 8, 1, 3);
        // El Stream ordena los números de forma natural (de menor a mayor).
        numerosSorted.stream()
                // La operación `sorted` ordena los elementos del Stream.
                // Si no se le pasa un `Comparator`, usa el orden natural.
                .sorted()
                .forEach(System.out::println); // Salida: 1, 2, 3, 5, 8
        System.out.println();

        // Ejemplo B: Ordenar por un criterio específico
        // Se crea una lista de palabras.
        List<String> palabras = List.of("Java", "es", "funcional");
        // Se ordena por la longitud de la palabra.
        palabras.stream()
                // Se usa `sorted` con un `Comparator` para definir un
                // criterio de ordenación personalizado.
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println); // Salida: es, Java, funcional
        System.out.println();

        // --- 5. Ejemplo Integrador (Combinando varias operaciones) ---
        System.out.println("--- Ejemplo 5: Pipeline de Streams ---");

        // Se crea una lista de frases.
        List<String> frases = List.of(
                "Java es un lenguaje versátil",
                "La programación funcional en Java es poderosa",
                "Java tiene streams para procesamiento de datos"
        );

        frases.stream()
                // `flatMap`: Esta es una operación de "aplanamiento".
                // Transforma cada frase en un Stream de palabras y luego
                // "aplana" todos esos Streams en uno solo.
                // `frase.split(" ")` divide cada frase en palabras.
                .flatMap(frase -> Arrays.stream(frase.split(" ")))
                // `map`: Convierte cada palabra a minúsculas para unificar el texto.
                .map(String::toLowerCase)
                // `distinct`: Elimina las palabras duplicadas del Stream.
                .distinct()
                // `sorted`: Ordena las palabras restantes alfabéticamente.
                .sorted()
                // `forEach`: Imprime cada palabra del Stream final.
                .forEach(System.out::println);
        /* Salida:
           datos
           en
           es
           funcional
           java
           la
           lenguaje
           para
           poderosa
           programación
           procesamiento
           streams
           tiene
           un
           versátil
        */
    }
}