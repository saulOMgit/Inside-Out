package dev.saul.models;
//modelo
public enum Emocion {
    ALEGRIA,
    TRISTEZA,
    IRA,
    ASCO,
    MIEDO,
    ANSIEDAD,
    ENVIDIA,
    VERGUENZA,
    ABURRIMIENTO,
    NOSTALGIA;

    // public static Emocion fromInt(int opcion) {
    //     return Emocion.values()[opcion - 1];
    // }

    // //esto en una vista
    // public static void mostrarOpciones() {
    //     for (int i = 0; i < Emocion.values().length; i++) {
    //         System.out.println((i + 1) + ". " + Emocion.values()[i].name().charAt(0) + Emocion.values()[i].name().substring(1).toLowerCase());
    //     }
    // }
}
