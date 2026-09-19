package generaWav;

public class GeneraWAV {

    boolean esWAV = true;
    String cadWAV = ".wav";

    /**
     * realizar la creación efectiva del archivo WAV y validar los valores
     */
    public void GeneraWAV() {

    }

    /**
     * La funcionalidad de la API se expone a través del método escribe. Crea el
     * archivo de audio de onda sinuseidal. Realizar validaciones de 2.
     * Comportamiento y Validaciones
     */
    public void escribe(String nombre, int iTiempo,
            int iFrecuenciaMuestreo, int iArmonico) {

        for (int i = 3; i >= 0 && esWAV; i--) {
            if (!(nombre.charAt(nombre.length() - 1 - i) == cadWAV.charAt(3 - i))) {
                esWAV = false;
                throw new IllegalArgumentException("archivo no wav");
            }
        }

        if (nombre == null) {
            throw new java.lang.NullPointerException();
        }

        if (nombre.isEmpty()) {
            throw new java.lang.IllegalArgumentException();
        }

        if (iTiempo <= 0 || iFrecuenciaMuestreo <= 0 || iArmonico <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        if (iArmonico > 20000) {
            throw new java.lang.IllegalArgumentException();
        }
        /*
        java.lang.NullPointerException: Se lanza si el parámetro nombre es nulo.
        java.lang.IllegalArgumentException: Se lanza bajo las siguientes condiciones:
            o Si los valores de iTiempo, iFrecuenciaMuestreo o armonico son menor que 0.
            o Si el valor de armonico es superior a 20000 Hz, lo cual excede el límite de
            escucha humana. Tambien si el armonico es inferior a 15430
            o Si el parámetro name es una cadena vacía, solo contiene espacios en blanco, o no
            tiene un nombre válido y la extensión .wav.
         */

    }

}
