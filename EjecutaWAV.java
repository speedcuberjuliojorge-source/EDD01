package generaWav;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Punto de entrada principal para la aplicación de generación de archivos de
 * sonido WAV. Su diseño se enfoca en la invocación por consola y la propagación
 * explícita de errores mediante excepciones.
 *
 * @author julio_cubes
 */
public class EjecutaWAV {

    /**
     * Metodo main del programa
     *
     * @param args
     * @throws java.io.IOException
     * @throws java.lang.IllegalArgumentException
     */
    public static void main(String args[]) throws java.io.IOException,
            java.lang.IllegalArgumentException {

        //DECLARACION DE VARIABLES
        String archControl = "";
        BufferedReader lectorArch;
        String nombreArchAud;
        int frecuencia_muestreo;
        int senial_armonica;
        int duracion;
        GeneraWAV generaWav;

        if (args.length == 1) {
            archControl = args[0];
        } else {
            throw new java.lang.IllegalArgumentException();
        }

        /*Verificacion de que se hayan proporcionado
        el número correcto de argumentos de línea de comandos.*/
        if (args.length == 1) {

            //Se comprueba si existe el archivo de control
            File fArchControl = new File(archControl);
            /*Si si existe el archivo de control, se lee el archivo de control
            y se guardan los valores*/
            if (fArchControl.exists()) {
                lectorArch = new BufferedReader(new FileReader(archControl));
                System.out.println(">> " + archControl);
                nombreArchAud = lectorArch.readLine();
                System.out.println("Nombre arch. de audio: " + nombreArchAud);
                try {
                    frecuencia_muestreo = Integer.parseInt(lectorArch.readLine());
                    System.out.println("Frecuencia de muestreo: " + frecuencia_muestreo);
                } catch (java.lang.IllegalArgumentException e) {
                    throw new java.lang.IllegalArgumentException();

                }

                try {
                    senial_armonica = Integer.parseInt(lectorArch.readLine());
                    System.out.println("Señal armonica: " + senial_armonica);
                } catch (java.lang.IllegalArgumentException e) {
                    throw new java.lang.IllegalArgumentException();
                }

                try {
                    duracion = Integer.parseInt(lectorArch.readLine());
                    System.out.println("Duracion de audio: " + duracion);
                } catch (java.lang.IllegalArgumentException e) {
                    throw new java.lang.IllegalArgumentException();
                }
                lectorArch.close();
                //Si no existe el archivo de control, se tira una exepcion
            } else {
                throw new java.io.FileNotFoundException(/*"No se encontro el archivo de control"*/);
            }

            /*En caso de recibir más parametros de los esperados,
            se tira una exepcion*/
        } else {
            throw new java.lang.IllegalArgumentException(/*"Numero de paramentros args[] incorrecto"*/);
        }

        //invocar a la clase generaWav.GeneraWAV para realizar la creación efectiva del archivo WAV.
        generaWav = new GeneraWAV();

        generaWav.escribe(nombreArchAud, duracion, frecuencia_muestreo, duracion);
        /*
        EXEPCIONES QUE PUEDE LANZAR EL PROGRAMA
        java.lang.IllegalArgumentException:Si el número de argumentos de línea de comandos es distinto de uno.
        NumberFormatException: Si un valor en el archivo de configuración no puede ser parseado a un entero válido
        java.io.FileNotFoundException: Si el archivo de configuración especificado por el argumento de línea de comandos no existe.
        java.io.IOException: Si ocurre cualquier problema durante la lectura del archivo de configuración (ej. archivo incompleto, problemas de permisos, fin de archivo inesperado).
        Cualquier excepción (ej. java.lang.IllegalArgumentException, java.lang.NullPointerException) que sea lanzada por la clase generaWav.GeneraWAV debido a validaciones de los parámetros de generación del WAV.
         */
    }

}
