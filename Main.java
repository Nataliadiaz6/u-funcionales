import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        //Ejemplo 1 – Creación de Optional
        Optional<String> opt1 = Optional.of("Hola");
        Optional<String> opt2 = Optional.ofNullable(null);
        Optional<String> opt3 = Optional.empty();

        System.out.println(opt1);
        System.out.println(opt2);
        System.out.println(opt3);

        //Ejemplo 2 – Uso básico (isPresent, ifPresent, orElse)
        Optional<String> nombre = Optional.ofNullable(null);

        // Forma clásica con isPresent
        if (nombre.isPresent()) {
            System.out.println("El nombre es: " + nombre.get());
        } else {
            System.out.println("No hay nombre");
        }

        // Forma funcional con orElse
        System.out.println(nombre.orElse("Desconocido"));

        // Forma más elegante con ifPresent
        nombre.ifPresent(n -> System.out.println("El nombre es: " + n));
        

        //Ejemplo 3 – Búsqueda en una lista con Optional

        List<String> nombres = List.of( "Luis", "María");

        Optional<String> encontrado = nombres.stream()
                .filter(n -> n.equals("Ana"))
                .findFirst();

        System.out.println(encontrado.orElse("No encontrado"));

        //Ejemplo 4 – Optional con orElseThrow

        String resultado = encontrado.orElseThrow(() -> new RuntimeException("No se encontró el valor"));
        System.out.println(resultado);
    }
}
