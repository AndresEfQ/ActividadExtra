package Servicios;

import Entidades.Tienda;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ServicioTienda {

    private final Tienda tienda = new Tienda();
    private final Scanner sc = new Scanner(System.in);

    public void ejecutarMenu() {
        System.out.println("Bienvenido a la tienda de alquiler de películas");
        this.mostrarMenu();
        while (this.tienda.isActive()) {
            int opcion = Integer.parseInt(sc.nextLine());
            while (opcion < 1 || opcion > 8) {
                System.out.println("Opción incorrecta, elija una opción entre 1 y 8");
                opcion = Integer.parseInt(sc.nextLine());
            }
            switch (opcion) {
                case 1 -> this.cargarPelicula();
                case 2 -> this.verPeliculasDisponibles();
                case 3 -> this.alquilarPelicula();
                case 4 -> this.verPeliculasAlquiladas();
                case 5 -> this.buscarPelicula();
                case 6 -> this.buscarFechaAlquiler();
                case 7 -> this.mostrarMenu();
                case 8 -> this.salir();
            }
            if (opcion != 8) {
                System.out.println("Qué desea hacer a continuación? Presione 7 para mostrar nuevamente el menú, 8 para salir");
            }
        }
    }

    // Opción 1.
    public void cargarPelicula() {

        ServicioPelicula[] nuevaPelicula = new ServicioPelicula[]{new ServicioPelicula()};
        nuevaPelicula[0].crearPelicula();

        ServicioPelicula[] prevDisponibles = this.tienda.getDisponibles();
        ServicioPelicula[] currDisponibles = Arrays.copyOf(prevDisponibles, prevDisponibles.length+1);
        System.arraycopy(nuevaPelicula, 0, currDisponibles, prevDisponibles.length, 1);

        this.tienda.setDisponibles(currDisponibles);
    }

    // Opción 2.
    public void verPeliculasDisponibles() {
        int contador = 1;
        boolean hayDisponibles = false;
        for (ServicioPelicula pelicula : this.tienda.getDisponibles()) {
            if (pelicula == null) {
                continue;
            }
            if (contador == 1) {
                System.out.println("Las siguientes películas se encuentran disponibles:");
            }
            System.out.println(contador + ": " + pelicula.listarPelicula());
            contador++;
            hayDisponibles = true;
        }
        if (!hayDisponibles) {
            System.out.println("No existen películas disponibles");
        }
    }

    // Opción 3.
    public void alquilarPelicula() {

        System.out.println("Elija el número de la película que desea alquilar");
        seleccionarPeliculaDisponible();

        ServicioPelicula pelicula;
        int numeroPelicula;
        ServicioPelicula[] disponibles;

        do {
            numeroPelicula = Integer.parseInt(sc.nextLine());
            while (numeroPelicula < 1 || numeroPelicula > this.tienda.getDisponibles().length) {
                System.out.println("La opción no corresponde a una película disponible");
                numeroPelicula = Integer.parseInt(sc.nextLine());
            }

            disponibles = this.tienda.getDisponibles();
            pelicula = disponibles[numeroPelicula-1];

            if (pelicula == null) {
                System.out.println("No es posible alquilar esta película porque se encuentra alquilada");
                System.out.println("Por favor seleccione otra película");
            }

        } while (pelicula == null);

        ServicioAlquiler[] nuevoAlquiler = new ServicioAlquiler[]{new ServicioAlquiler()};
        nuevoAlquiler[0].crearAlquiler(pelicula.getPelicula());
        ServicioAlquiler[] prevAlquiladas = this.tienda.getAlquiladas();
        ServicioAlquiler[] currAlquiladas = Arrays.copyOf(prevAlquiladas, prevAlquiladas.length+1);

        System.arraycopy(nuevoAlquiler, 0, currAlquiladas, prevAlquiladas.length, 1);
        disponibles[numeroPelicula-1] = null;

        this.tienda.setAlquiladas(currAlquiladas);
        this.tienda.setDisponibles(disponibles);
    }

    // Opción 4.
    public void verPeliculasAlquiladas() {

        boolean hayAlquiladas = false;
        int contador = 1;

        for (ServicioAlquiler alquilada : this.tienda.getAlquiladas()) {
            if (alquilada == null) {
                continue;
            }
            if (contador == 1) {
                System.out.println("Las siguientes películas se encuentran alquiladas:");
            }
            System.out.println(contador + ": " + alquilada.listarPeliculaAlquilada());
            hayAlquiladas = true;
            contador++;
        }
        if (!hayAlquiladas) {
            System.out.println("No se encontraron películas alquiladas");
        }
    }

    // Opción 5.
    public void buscarPelicula() {
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

            for (ServicioPelicula pelicula: this.tienda.getDisponibles()) {
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

            for (ServicioPelicula disponible : this.tienda.getDisponibles()) {
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

    // Opción 6.
    public void buscarFechaAlquiler() {
        System.out.println("Por favor ingrese la fecha del alquiler que desea buscar");

        System.out.print("Día (DD): ");
        int dia = Integer.parseInt(sc.nextLine());

        System.out.print("Mes (MM): ");
        int mes = Integer.parseInt(sc.nextLine());

        System.out.print("Año (AAAA): ");
        int anio = Integer.parseInt(sc.nextLine());

        Date fecha = new Date(anio-1900, mes-1, dia);
        boolean encontrado = false;

        for (ServicioAlquiler alquiler: this.tienda.getAlquiladas()) {
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

    // Opción 7.
    public void mostrarMenu() {

        System.out.println();
        System.out.println("1. Cargar una película");
        System.out.println("2. Ver películas disponibles");
        System.out.println("3. Alquilar película");
        System.out.println("4. Ver películas alquiladas");
        System.out.println("5. Buscar película");
        System.out.println("6. Buscar fecha de alquiler");
        System.out.println("7. Mostrar menú");
        System.out.println("8. Salir");
    }

    // Opción 8.
    public void salir() {
        System.out.println("Gracias por utilizar nuestro servicio");
        this.tienda.setActive(false);
    }

    public void seleccionarPeliculaDisponible() {
        int contador = 1;
        boolean hayDisponibles = false;
        for (ServicioPelicula pelicula : this.tienda.getDisponibles()) {
            if (pelicula == null) {
                System.out.println(contador + ": Se encuentra alquilada" );
                contador++;
            } else {
                System.out.println(contador + ": " + pelicula.listarPelicula());
                contador++;
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("No existen películas disponibles");
        }
    }
}
