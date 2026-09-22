package generaWav;

/**
 * Realizar la creación efectiva del archivo WAV y validar los valores
 *
 * @author julio_cubes
 */
public class GeneraWAV {

    //Atributos
    private boolean esWAV = true;
    private String cadWAV = ".wav";
    private boolean nombreVacio;

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
        for (int i = 3; i >= 0 && isEsWAV(); i--) {
            if (!(nombre.charAt(nombre.length() - 1 - i) == cadWAV.charAt(3 - i))) {
                setEsWAV(false);
                throw new IllegalArgumentException("archivo sin extension .wav");
            }
        }

        //archivo wav con solo extension
        setNombreVacio(false);
        for (int i = 0; (i < nombre.length() - 4) && !isNombreVacio(); i++) {
            if (nombre.charAt(i) != ' ') {
                setNombreVacio(true);
            }
        }
        if (!isNombreVacio() || (nombre == ".wav")) {
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

    ///////////GETTERS Y SETTERS///////////
    public boolean isEsWAV() {
        return esWAV;
    }

    public void setEsWAV(boolean esWAV) {
        this.esWAV = esWAV;
    }

    public String getCadWAV() {
        return cadWAV;
    }

    public void setCadWAV(String cadWAV) {
        this.cadWAV = cadWAV;
    }

    public boolean isNombreVacio() {
        return nombreVacio;
    }

    public void setNombreVacio(boolean nombreVacio) {
        this.nombreVacio = nombreVacio;
    }

}
