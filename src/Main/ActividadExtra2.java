package Main;

import Entidades.Pelicula;
import Servicios.ServicioAlquiler;
import Servicios.ServicioPelicula;

import java.util.Date;
import java.util.Scanner;

public class ActividadExtra2 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ServicioPelicula[] peliculasDisponibles = new ServicioPelicula[5];
        ServicioAlquiler[] peliculasAlquiladas = new ServicioAlquiler[3];
        int contPeliculas = 0;
        int contAlquileres = 0;

        boolean activo = true;

        System.out.println("Bienvenido al alquiler de películas, elija la opción que desea realizar:");

        while (activo) {
            mostrarMenu();
            int opcion = Integer.parseInt(sc.nextLine());
            while (opcion < 1 || opcion > 7) {
                System.out.println("Opción incorrecta, elija una opción entre 1 y 7");
                opcion = Integer.parseInt(sc.nextLine());
            }
            switch (opcion) {
                case 1 -> {
                    peliculasDisponibles[contPeliculas] = new ServicioPelicula();
                    peliculasDisponibles[contPeliculas].crearPelicula();
                    contPeliculas++;
                }
                case 2 -> imprimirArrayDisponibles(peliculasDisponibles);
                case 3 -> {
                    System.out.println("Elija el número de la película que desea alquilar");
                    imprimirArrayDisponibles(peliculasDisponibles);
                    int numeroPelicula = Integer.parseInt(sc.nextLine());
                    while (numeroPelicula < 1 || numeroPelicula > peliculasDisponibles.length) {
                        System.out.println("La opción no corresponde a una película disponible");
                        numeroPelicula = Integer.parseInt(sc.nextLine());
                    }
                    peliculasAlquiladas[contAlquileres] = new ServicioAlquiler();
                    peliculasAlquiladas[contAlquileres].crearAlquiler(peliculasDisponibles[numeroPelicula - 1].getPelicula());
                    peliculasDisponibles[numeroPelicula - 1] = null;
                    contAlquileres++;
                }
                case 4 -> imprimirArrayAlquiladas(peliculasAlquiladas);
                case 5 -> buscarPelicula(peliculasDisponibles);
                case 6 -> buscarAlquiler(peliculasAlquiladas);
                case 7 -> activo = false;
            }
        }


    }

    public static void mostrarMenu() {

        System.out.println();
        System.out.println("1. Cargar una película");
        System.out.println("2. Ver películas disponibles");
        System.out.println("3. Alquilar película");
        System.out.println("4. Ver películas alquiladas");
        System.out.println("5. Buscar película");
        System.out.println("6. Buscar fecha de alquiler");
        System.out.println("7. Salir");
    }

    public static void imprimirArrayDisponibles(ServicioPelicula[] disponibles) {
        int contador = 1;
        for (ServicioPelicula pelicula : disponibles) {
            if (pelicula == null) {
                if (contador == 1) {
                    System.out.println("No existen películas disponibles");
                }
                continue;
            }
            System.out.println(contador + ": " + pelicula.listarPelicula());
            contador++;
        }
    }

    public static void imprimirArrayAlquiladas(ServicioAlquiler[] alquiladas) {

        boolean encontrada = false;

        for (ServicioAlquiler alquilada : alquiladas) {
            if (alquilada == null) {
                continue;
            }
            System.out.println(alquilada.listarPeliculaAlquilada());
            encontrada = true;
        }
        if (!encontrada) {
            System.out.println("No se encontraron películas alquiladas");
        }
    }

    public static void buscarPelicula(ServicioPelicula[] disponibles) {
        System.out.println("Desea buscar la película por titulo (T) o género (G)");
        String opcion = sc.nextLine();
        while (!opcion.equalsIgnoreCase("T") && !opcion.equalsIgnoreCase("G")) {
            System.out.println("Opción inválida, por favor elija 'T' o 'G'");
            opcion = sc.nextLine();
        }
        if (opcion.equalsIgnoreCase("T")) {
            System.out.println("Ingrese el título de la película que desea buscar");

            String titulo = sc.nextLine();
            boolean encontrada = false;

            for (ServicioPelicula pelicula: disponibles) {
                if (pelicula.buscarPeliculaPorTitulo(titulo)) {
                    System.out.println("La película " + titulo + " se encuentra disponible:");
                    System.out.println(pelicula.getPelicula());
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("La película " + titulo + " no se encuentra disponible.");
            }
        } else {
            System.out.println("Ingrese el género de la película que desea buscar");

            String genero = sc.nextLine();
            boolean encontrada = false;

            for (ServicioPelicula disponible : disponibles) {
                if (disponible.buscarPeliculaPorGenero(genero)) {
                    if (!encontrada) {
                        System.out.println("Las siguientes películas del género " + genero + " se encuentran disponibles:");
                    }
                    System.out.println(disponible.getPelicula());
                    encontrada = true;
                }
            }

            if (!encontrada) {
                System.out.println("No se encontraron películas del género " + genero + " .");
            }
        }
    }

    public static void buscarAlquiler(ServicioAlquiler[] alquiladas) {
        System.out.println("Por favor ingrese la fecha del alquiler que desea buscar");

        System.out.print("Día (DD): ");
        int dia = Integer.parseInt(sc.nextLine());

        System.out.print("Mes (MM): ");
        int mes = Integer.parseInt(sc.nextLine());

        System.out.print("Año (AAAA): ");
        int anio = Integer.parseInt(sc.nextLine());

        Date fecha = new Date(anio-1900, mes-1, dia);
        boolean encontrado = false;

        for (ServicioAlquiler alquiler: alquiladas) {
            if (alquiler == null) {
                continue;
            }
            if (alquiler.buscarAlquilerPorFecha(fecha)) {
                if (!encontrado) {
                    System.out.println("Se encontraron los siguientes alquileres en la fecha ingresada:");
                }
                System.out.println(alquiler.listarPeliculaAlquilada());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron alquileres en la fecha ingresada");
        }
    }
}
