package dev.saul.views;

import dev.saul.models.Momento;

import java.util.List;

public class DiarioView {

    public void mostrarMomentos(List<Momento> momentos) {
        if (momentos.isEmpty()) {
            System.out.println("No hay momentos guardados.");
        } else {
            momentos.forEach(System.out::println);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
