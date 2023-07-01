package Entidades;

import Servicios.ServicioAlquiler;
import Servicios.ServicioPelicula;

import java.util.Arrays;

public class Tienda {

    private ServicioPelicula[] disponibles = new ServicioPelicula[0];
    private ServicioAlquiler[] alquiladas = new ServicioAlquiler[0];
    private boolean isActive = true;

    public Tienda() {
    }

    public Tienda(ServicioPelicula[] disponibles, ServicioAlquiler[] alquiladas, boolean isActive) {
        this.disponibles = disponibles;
        this.alquiladas = alquiladas;
        this.isActive = isActive;
    }

    public ServicioPelicula[] getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(ServicioPelicula[] disponibles) {
        this.disponibles = disponibles;
    }

    public ServicioAlquiler[] getAlquiladas() {
        return alquiladas;
    }

    public void setAlquiladas(ServicioAlquiler[] alquiladas) {
        this.alquiladas = alquiladas;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "disponibles=" + Arrays.toString(disponibles) +
                ", alquiladas=" + Arrays.toString(alquiladas) +
                ", isActive=" + isActive +
                '}';
    }
}
