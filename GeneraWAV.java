package generaWav;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Realizar la creación efectiva del archivo WAV y validar los valores
 *
 * @author julio_cubes
 */
public class GeneraWAV {

    ///////ATRIBUTOS///////
    private byte riff[];
    private int tamano = 2084;//Tamaño del archivo – 8
    private byte wave[];
    private int formato;
    private short pcm;
    private short canales;
    private int frecuencia;
    private int f_muestreo;
    private short bytes_m;
    private short bits_m;
    private byte data[];
    private int bytes_archivo = 2084;//Número de bytes que ocupan las muestras

    //valores estandar del encabezado
    public GeneraWAV() {
        setRiff(new byte[]{'R', 'I', 'F', 'F'});
        setWave(new byte[]{'W', 'A', 'V', 'E', 'f', 'm', 't', ' '});
        setFormato(16);
        setPcm((short) 1);
        setCanales((short) 1);
        setF_muestreo(getFrecuencia() * 2);
        setBytes_m((short) 2);
        setBits_m((short) 16);
        setData(new byte[]{'d', 'a', 't', 'a'});
    }

    /**
     * La funcionalidad de la API se expone a través del método escribe. Crea el
     * archivo de audio de onda sinuseidal. Realizar validaciones de 2.
     * Comportamiento y Validaciones
     */
    public void escribe(String nombre, int iTiempo,
            int iFrecuenciaMuestreo, int iArmonico) {

        //VARIABLES PARA LAS VALIDACIONES
        boolean esWAV = true;
        String cadWAV = ".wav";
        boolean nombreVacio;

        /////////////VALIDACIONES/////////////
        //archivo sin extension .wav
        for (int i = 3; i >= 0 && esWAV && !nombre.isEmpty(); i--) {
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

        //Pasando la frecuencia del parametro a su varible local
        this.setFrecuencia(iFrecuenciaMuestreo);

        ///////////////////////// ESCRITURA DEL ARCHIVO.WAV /////////////////////////
        try (FileOutputStream writeWAV = new FileOutputStream(nombre)) {

            writeWAV.write(riff);
            writeWAV.write(tamano);
            writeWAV.write(tamano >> 8);
            writeWAV.write(tamano >> 16);
            writeWAV.write(tamano >> 24);
            writeWAV.write(wave);
            writeWAV.write(formato);
            writeWAV.write(formato >> 8);
            writeWAV.write(formato >> 16);
            writeWAV.write(formato >> 24);
            writeWAV.write(pcm);
            writeWAV.write(pcm >> 8);
            writeWAV.write(canales);
            writeWAV.write(canales >> 8);
            writeWAV.write(frecuencia);
            writeWAV.write(frecuencia >> 8);
            writeWAV.write(frecuencia >> 16);
            writeWAV.write(frecuencia >> 24);
            writeWAV.write(f_muestreo);
            writeWAV.write(f_muestreo >> 8);
            writeWAV.write(f_muestreo >> 16);
            writeWAV.write(f_muestreo >> 24);
            writeWAV.write(bytes_m);
            writeWAV.write(bytes_m >> 8);
            writeWAV.write(bits_m);
            writeWAV.write(bits_m >> 8);
            writeWAV.write(data);
            writeWAV.write(bytes_archivo);
            writeWAV.write(bytes_archivo >> 8);
            writeWAV.write(bytes_archivo >> 16);
            writeWAV.write(bytes_archivo >> 24);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////// Getters y Setters /////////////////////////
    public byte[] getRiff() {
        return riff;
    }

    public void setRiff(byte[] riff) {
        this.riff = riff;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public byte[] getWave() {
        return wave;
    }

    public void setWave(byte[] wave) {
        this.wave = wave;
    }

    public int getFormato() {
        return formato;
    }

    public void setFormato(int formato) {
        this.formato = formato;
    }

    public short getPcm() {
        return pcm;
    }

    public void setPcm(short pcm) {
        this.pcm = pcm;
    }

    public short getCanales() {
        return canales;
    }

    public void setCanales(short canales) {
        this.canales = canales;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getF_muestreo() {
        return f_muestreo;
    }

    public void setF_muestreo(int f_muestreo) {
        this.f_muestreo = f_muestreo;
    }

    public short getBytes_m() {
        return bytes_m;
    }

    public void setBytes_m(short bytes_m) {
        this.bytes_m = bytes_m;
    }

    public short getBits_m() {
        return bits_m;
    }

    public void setBits_m(short bits_m) {
        this.bits_m = bits_m;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getBytes_archivo() {
        return bytes_archivo;
    }

    public void setBytes_archivo(int bytes_archivo) {
        this.bytes_archivo = bytes_archivo;
    }

}
