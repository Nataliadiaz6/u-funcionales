import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main{
    /**
     * El método 'main' es el punto de entrada principal del programa.
     * Aquí se ejecutan todos los ejemplos.
     *
     * @param args Argumentos de la línea de comandos (no se usan en este ejemplo).
     */
    public static void main(String[] args) {

        System.out.println("--- Ejemplo 1: Nuestra propia interfaz 'Adder' ---");
        // En lugar de crear una clase que implemente 'Adder',
        // se usa una expresión lambda para implementar el método 'add' al vuelo.
        // `(a, b) -> a + b` es la implementación del método.
        Adder adder = (a, b) -> a + b;
        System.out.println("Suma de 5 + 3: " + adder.add(5, 3)); // Salida: 8
        System.out.println(); // Salto de línea


        System.out.println("--- Ejemplo 2: Más interfaces propias ---");
        // La misma lógica se aplica a otras operaciones matemáticas.

        // Lambda para restar
        Subtractor subtractor = (a, b) -> a - b;
        System.out.println("Resta de 10 - 4: " + subtractor.subtract(10, 4)); // Salida: 6

        // Lambda para multiplicar
        Multiplier multiplier = (a, b) -> a * b;
        System.out.println("Multiplicación de 6 * 7: " + multiplier.multiply(6, 7)); // Salida: 42

        // Lambda para dividir. Se realiza un 'cast' a 'double' para obtener un resultado preciso.
        Divider divider = (a, b) -> (double) a / b;
        System.out.println("División de 20 / 4: " + divider.divide(20, 4)); // Salida: 5.0
        System.out.println();


        System.out.println("--- Ejemplo 3: Interfaces funcionales de Java ---");
        // Se usan las interfaces funcionales predefinidas en el paquete `java.util.function`.

        // Predicate: Recibe un valor y devuelve un booleano (true/false).
        // Se usa para probar una condición.
        Predicate<Integer> esPar = n -> n % 2 == 0;
        System.out.println("¿Es 4 un número par? " + esPar.test(4)); // Salida: true

        // Function: Recibe un valor de un tipo y devuelve un valor de otro tipo.
        // Se usa para transformar un valor.
        Function<String, Integer> longitud = s -> s.length();
        System.out.println("La longitud de 'Hola' es: " + longitud.apply("Hola")); // Salida: 4

        // Consumer: Recibe un valor y no devuelve nada.
        // Se usa para "consumir" o realizar una acción con el valor, como imprimirlo.
        Consumer<String> imprimir = s -> System.out.println(s);
        System.out.print("Consumer imprimiendo: ");
        imprimir.accept("Hola mundo"); // Salida: Hola mundo

        // Supplier: No recibe argumentos, pero devuelve un valor.
        // Se usa para "suministrar" un valor.
        Supplier<Double> random = () -> Math.random();
        System.out.println("Número aleatorio generado por un Supplier: " + random.get());
        System.out.println();


        System.out.println("--- Ejemplo 4: Conexión con Streams ---");
        // Los Streams utilizan internamente estas interfaces funcionales.

        List<String> nombres = Arrays.asList("Ana", "Pedro", "Juan");

        // Se encadenan operaciones de Stream.
        nombres.stream()
                // `filter`: Espera un `Predicate<String>`. La lambda filtra los nombres de más de 3 letras.
                .filter(n -> n.length() > 3)

                // `map`: Espera un `Function<String, String>`. El método de referencia
                // `String::toUpperCase` es una forma abreviada de una lambda que transforma
                // cada cadena a mayúsculas.
                .map(String::toUpperCase)

                // `forEach`: Espera un `Consumer<String>`. El método de referencia
                // `System.out::println` es una lambda que imprime cada elemento.
                .forEach(System.out::println); // Salida: PEDRO, JUAN
    }
}

