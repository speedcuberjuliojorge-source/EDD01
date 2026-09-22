package generaWav;

/**
 * Realizar la creación efectiva del archivo WAV y validar los valores
 *
 * @author julio_cubes
 */
public class GeneraWAV {

    //Atributos
    boolean esWAV = true;
    String cadWAV = ".wav";
    boolean nombreVacio;

    /**
     * Constructor
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

        /////////////VALIDACIONES/////////////
        //archivo sin extension .wav
        for (int i = 3; i >= 0 && esWAV; i--) {
            if (!(nombre.charAt(nombre.length() - 1 - i) == cadWAV.charAt(3 - i))) {
                esWAV = false;
                throw new IllegalArgumentException("archivo sin extension .wav");
            }
        }

        //archivo wav con solo extension
        nombreVacio = false;
        for (int i = 0; (i < nombre.length() - 4) && !nombreVacio; i++) {
            if (nombre.charAt(i) != ' ') {
                nombreVacio = true;
            }
        }
        if (!nombreVacio || (nombre == ".wav")) {
            throw new IllegalArgumentException("archivo wav con solo extension");
        }

        //Nombre del archivo nulo
        if (nombre == null) {
            throw new java.lang.NullPointerException("Nombre del archivo nulo");
        }

        //Nombre del archivo vacio
        if (nombre.isEmpty()) {
            throw new java.lang.IllegalArgumentException("Nombre del archivo vacio");
        }

        //Tiempo, frecuencia de muestreo y/o armonico con valores invalidos
        if (iTiempo <= 0 || iFrecuenciaMuestreo <= 0 || iArmonico <= 0) {
            throw new java.lang.IllegalArgumentException("Tiempo, frecuencia de muestreo y/o armonico con valores invalidos");
        }

        //Armonico con valor superior a 20000
        if (iArmonico > 20000) {
            throw new java.lang.IllegalArgumentException("Armonico con valor superior a 20000");
        }
    }

}
