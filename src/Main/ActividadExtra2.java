package Main;

import Entidades.Pelicula;
import Servicios.ServicioAlquiler;
import Servicios.ServicioPelicula;

import java.util.Scanner;

public class ActividadExtra2 {

    public static void main(String[] args) {

        ServicioPelicula[] peliculasDisponibles = new ServicioPelicula[5];
        ServicioAlquiler[] peliculasAlquiladas = new ServicioAlquiler[3];
        int contPeliculas = 0;
        int contAlquileres = 0;
        Scanner sc = new Scanner(System.in);
        boolean activo = true;
        while (activo) {
            mostrarMenu();
            int opcion = Integer.parseInt(sc.nextLine());
            while (opcion < 1 || opcion > 7) {
                System.out.println("Opción incorrecta, elija una opción entre 1 y 7");
                opcion = Integer.parseInt(sc.nextLine());
            }
            switch (opcion) {
                case 1:
                    peliculasDisponibles[contPeliculas] = new ServicioPelicula();
                    peliculasDisponibles[contPeliculas].crearPelicula();
                    contPeliculas++;
                    break;
                case 2:
                    imprimirArrayDisponibles(peliculasDisponibles);
                    break;
                case 3:
                    System.out.println("Elija el número de la película que desea alquilar");
                    imprimirArrayDisponibles(peliculasDisponibles);
                    int numeroPelicula = Integer.parseInt(sc.nextLine());
                    peliculasAlquiladas[contAlquileres] = new ServicioAlquiler();
                    peliculasAlquiladas[contAlquileres].crearAlquiler(peliculasDisponibles[numeroPelicula-1].getPelicula());
                    peliculasDisponibles[numeroPelicula-1] = null;
                    contAlquileres++;
                    break;
                case 4:
                    imprimirArrayAlquiladas(peliculasAlquiladas);
                    break;
                case 5:

            }
        }


    }

    public static void mostrarMenu() {

        System.out.println("Bienvenido al alquiler de películas, elija la opción que desea realizar:");
        System.out.println();
        System.out.println("1. Cargar una película");
        System.out.println("2. Ver películas disponibles");
        System.out.println("3. Alquilar película");
        System.out.println("4. Ver películas alquiladas");
        System.out.println("5. Buscar película");
        System.out.println("6. Buscar alquiler");
        System.out.println("7. Salir");
    }

    public static void imprimirArrayDisponibles(ServicioPelicula[] disponibles) {
        int contador = 1;
        for (ServicioPelicula pelicula : disponibles) {
            System.out.println(contador + ": " + pelicula.listarPelicula());
            contador++;
        }
    }

    public static void imprimirArrayAlquiladas(ServicioAlquiler[] alquiladas) {
        for (ServicioAlquiler alquilada : alquiladas) {
            System.out.println(alquilada.listarPeliculaAlquilada());
        }
    }

    public static void buscarPelicula(ServicioPelicula[] disponibles) {
        System.out.println("Desea buscar la película por titulo (T) o género (G)");

    }
}
