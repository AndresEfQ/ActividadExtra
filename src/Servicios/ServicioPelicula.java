package Servicios;

import Entidades.Pelicula;

import java.util.Scanner;

public class ServicioPelicula {

    private Pelicula pelicula = new Pelicula();

    public void crearPelicula() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor ingrese los datos de la película");

        System.out.print("Título: ");
        this.pelicula.setTitulo(sc.nextLine());

        System.out.print("Genero: ");
        this.pelicula.setGenero(sc.nextLine());

        System.out.print("Año: ");
        this.pelicula.setAnio(Integer.parseInt(sc.nextLine()));

        System.out.print("Duración en minutos: ");
        this.pelicula.setDuracion(Integer.parseInt(sc.nextLine()));
    }

    public String listarPelicula() {
        return this.pelicula.getTitulo();
    }

    public boolean buscarPeliculaPorTitulo(String titulo) {
        return this.pelicula.getTitulo().equalsIgnoreCase(titulo);
    }

    public boolean buscarPeliculaPorGenero(String genero) {
        return this.pelicula.getGenero().equalsIgnoreCase(genero);
    }

    public Pelicula getPelicula() {
        return this.pelicula;
    }
}
