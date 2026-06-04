import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Esta clase compara el rendimiento y comportamiento de los Streams secuenciales y paralelos
 * en Java. Se demuestra cómo los Streams paralelos pueden acelerar el procesamiento de
 * grandes conjuntos de datos, pero también se resaltan los riesgos relacionados con
 * el orden de los elementos y los efectos secundarios.
 */
public class Main {

    /**
     * El método `main` es el punto de entrada principal del programa.
     * Aquí se ejecutan todos los ejemplos.
     *
     * @param args Argumentos de la línea de comandos (no se usan en este ejemplo).
     */
    public static void main(String[] args) {

        // Se crea una lista grande de 10 millones de números para demostrar la diferencia
        // de rendimiento entre Streams secuenciales y paralelos.
        List<Integer> numerosGrandes = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .toList();

        // --- 1. Ejemplo de procesamiento secuencial ---
        System.out.println("--- Ejemplo 1: Stream Secuencial ---");
        long inicioSecuencial = System.currentTimeMillis();

        // El Stream secuencial procesa los elementos uno por uno en un solo hilo.
        long sumaSecuencial = numerosGrandes.stream()
                // Se filtran solo los números pares.
                .filter(n -> n % 2 == 0)
                // Se eleva cada número al cuadrado. `mapToLong` es más eficiente
                // para operaciones matemáticas.
                .mapToLong(n -> (long) n * n)
                // Se suman los resultados.
                .sum();

        long tiempoSecuencial = System.currentTimeMillis() - inicioSecuencial;

        System.out.println("Suma secuencial: " + sumaSecuencial);
        System.out.println("Tiempo secuencial: " + tiempoSecuencial + " ms");
        System.out.println();

        // --- 2. Ejemplo de procesamiento paralelo ---
        System.out.println("--- Ejemplo 2: Stream Paralelo ---");
        long inicioParalelo = System.currentTimeMillis();

        // `parallelStream()` divide la lista en segmentos más pequeños y procesa
        // cada segmento en un hilo diferente, aprovechando los múltiples núcleos del CPU.
        long sumaParalela = numerosGrandes.parallelStream()
                .filter(n -> n % 2 == 0)
                .mapToLong(n -> (long) n * n)
                .sum();

        long tiempoParalelo = System.currentTimeMillis() - inicioParalelo;

        System.out.println("Suma paralela: " + sumaParalela);
        System.out.println("Tiempo paralelo: " + tiempoParalelo + " ms");
        System.out.println("Se puede observar una mejora considerable en el tiempo de ejecución.");
        System.out.println();

        // --- 3. Ejemplo de diferencia en el orden ---
        System.out.println("--- Ejemplo 3: El Orden de los Resultados ---");
        List<String> nombres = List.of("Ana", "Luis", "María", "Carlos", "Elena");

        System.out.println("Secuencial (orden garantizado):");
        // El Stream secuencial mantiene el orden original de los elementos.
        nombres.stream().forEach(System.out::println);
        /* Salida:
           Ana
           Luis
           María
           Carlos
           Elena
        */
        System.out.println();

        System.out.println("Paralelo (orden no garantizado):");
        // El Stream paralelo no garantiza el orden. Los hilos terminan en
        // momentos diferentes, lo que puede resultar en una salida desordenada.
        nombres.parallelStream().forEach(System.out::println);
        /* Salida (ejemplo, puede variar):
           María
           Ana
           Carlos
           Luis
           Elena
        */
        System.out.println();

        System.out.println("Paralelo con forEachOrdered (se mantiene el orden):");
        // Si se necesita mantener el orden en un Stream paralelo, se usa `forEachOrdered`.
        // Esto tiene un costo de rendimiento, ya que los resultados se sincronizan.
        nombres.parallelStream().forEachOrdered(System.out::println);
        /* Salida:
           Ana
           Luis
           María
           Carlos
           Elena
        */
        System.out.println();

        // --- 4. Ejemplo de riesgo con efectos secundarios (Side Effects) ---
        System.out.println("--- Ejemplo 4: Riesgo de efectos secundarios en paralelismo ---");
        List<Integer> lista = IntStream.rangeClosed(1, 1000).boxed().toList();

        // Se usa un array de un solo elemento para simular una variable compartida mutable.
        int[] suma = {0};

        // Esta operación es peligrosa en Streams paralelos. Varios hilos intentan
        // modificar la variable `suma[0]` al mismo tiempo, lo que puede causar
        // una "condición de carrera" y un resultado incorrecto e impredecible.
        lista.parallelStream().forEach(n -> suma[0] += n);

        System.out.println("Suma con efectos secundarios (resultado incorrecto): " + suma[0]);
        System.out.println("El resultado correcto para la suma de 1 a 1000 es 500500.");
        System.out.println("Esta forma de acumular valores NO debe usarse con Streams paralelos.");
        System.out.println();

        // La forma correcta y segura para este tipo de operación es usar `sum()` o `reduce()`.
        long sumaSegura = lista.parallelStream().mapToLong(Integer::longValue).sum();
        System.out.println("Suma con operación segura (sum()): " + sumaSegura);
        System.out.println("La API de Streams de Java está diseñada para trabajar con operaciones sin estado.");
    }
}