import entidades.Persona;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Persona> personas = List.of(
                new Persona("Ana", 25),
                new Persona("Luis", 17),
                new Persona("María", 25),
                new Persona("Carlos", 30),
                new Persona("Elena", 17)
        );

        //Ejemplo 1 – groupingBy()

        Map<Integer, List<Persona>> porEdad = personas.stream()
                .collect(Collectors.groupingBy(p -> p.getEdad()));

        System.out.println(porEdad);


        //Ejemplo 2 – partitioningBy()

        Map<Boolean, List<Persona>> porMayoriaEdad = personas.stream()
                .collect(Collectors.partitioningBy(p -> p.getEdad() >= 18));

        System.out.println(porMayoriaEdad);

        //Ejemplo 3 – joining()

        String nombres = personas.stream()
                .map(Persona::getNombre)
                .collect(Collectors.joining(", "));

        System.out.println(nombres);
        
    }
}
