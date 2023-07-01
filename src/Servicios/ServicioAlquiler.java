package Servicios;

import Entidades.Alquiler;
import Entidades.Pelicula;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ServicioAlquiler {

    private Alquiler alquiler = new Alquiler();

    public void crearAlquiler(Pelicula pelicula) {

        Scanner sc = new Scanner(System.in);

        this.alquiler.setPelicula(pelicula);
        this.alquiler.setFechaInicio(new Date());

        System.out.println("Indique cuantos días desea alquilar la película");
        int dias = Integer.parseInt(sc.nextLine());

        Calendar c = Calendar.getInstance();
        c.setTime(this.alquiler.getFechaInicio());
        c.add(Calendar.DATE, dias);
        this.alquiler.setFechaFin(c.getTime());

        if (dias <= 3) {
            this.alquiler.setPrecio(10);
        } else {
            this.alquiler.setPrecio(10 * (Math.pow((1 + (double) 10 / 100), (dias - 3))));
        }
    }

    public String listarPeliculaAlquilada() {
        return this.alquiler.getPelicula().getTitulo();
    }

    public boolean buscarAlquilerPorFecha(Date fecha) {
        return !fecha.before(this.alquiler.getFechaInicio()) && !fecha.after(this.alquiler.getFechaFin());
    }
}
