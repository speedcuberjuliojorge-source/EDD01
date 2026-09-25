package generaWav;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Realizar la creación efectiva del archivo WAV y validar los valores
 *
 * @author julio_cubes
 */
public final class GeneraWAV {

    ///////ATRIBUTOS///////
    private byte[] riff;
    private int tamanio;//Tamaño del archivo 
    private byte[] wave;
    private int formato;
    private short pcm;
    private short canales;
    private int frecuencia;
    private int fMuestreo;
    private short bytesM;
    private short bitsM;
    private byte[] data;
    private int bytesArchivo;//Número de bytes que ocupan las muestras

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

        this.setTamano(((iFrecuenciaMuestreo * 16) + 352 / 8) - 8);
        this.setBytes_archivo(iFrecuenciaMuestreo * iTiempo * bytesM);

        /*
            ESCRIBIR NUMEROS SHORT EN COMPLEMENTO A 2
        short d = 15;
        int iD = (~d) + 1;
        d = (short) iD;

        System.out.println("");
         */
        //VARIABLES PARA LAS VALIDACIONES
        boolean esWAV = true;
        String cadWAV = ".wav";
        boolean nombreVacio;

        /////////////VALIDACIONES/////////////
        //archivo sin extension .wav
        for (int i = 3; i >= 0 && esWAV && !nombre.isEmpty(); i--) {
            if (!(nombre.charAt(nombre.length() - 1 - i)
                    == cadWAV.charAt(3 - i))) {
                esWAV = false;
                throw new IllegalArgumentException(""
                        + "archivo sin extension .wav");
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
            throw new IllegalArgumentException("archivo wav con solo "
                    + "extension");
        }

        //Nombre del archivo nulo
        if (nombre == null) {
            throw new java.lang.NullPointerException("Nombre del archivo nulo");
        }

        //Nombre del archivo vacio
        if (nombre.isEmpty()) {
            throw new java.lang.IllegalArgumentException("Nombre del archivo "
                    + "vacio");
        }

        //Tiempo, frecuencia de muestreo y/o armonico con valores invalidos
        if (iTiempo <= 0 || iFrecuenciaMuestreo <= 0 || iArmonico <= 0) {
            throw new java.lang.IllegalArgumentException("Tiempo, frecuencia de"
                    + " muestreo y/o armonico con valores invalidos");
        }

        //Armonico con valor superior a 20000
        if (iArmonico > 20000) {
            throw new java.lang.IllegalArgumentException("Armonico con valor "
                    + "superior a 20000");
        }

        //Pasando la frecuencia del parametro a su varible local
        this.setFrecuencia(iFrecuenciaMuestreo);

        ///////////////////////// ESCRITURA DEL ARCHIVO.WAV //////////////////
        try (FileOutputStream writeWAV = new FileOutputStream(nombre)) {

            writeWAV.write(riff);
            writeWAV.write(tamanio);
            writeWAV.write(tamanio >> 8);
            writeWAV.write(tamanio >> 16);
            writeWAV.write(tamanio >> 24);
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
            writeWAV.write(fMuestreo);
            writeWAV.write(fMuestreo >> 8);
            writeWAV.write(fMuestreo >> 16);
            writeWAV.write(fMuestreo >> 24);
            writeWAV.write(bytesM);
            writeWAV.write(bytesM >> 8);
            writeWAV.write(bitsM);
            writeWAV.write(bitsM >> 8);
            writeWAV.write(data);
            writeWAV.write(bytesArchivo);
            writeWAV.write(bytesArchivo >> 8);
            writeWAV.write(bytesArchivo >> 16);
            writeWAV.write(bytesArchivo >> 24);

            //Escribir muestras
            int dMuestra;
            short sMuestra;
            for (int i = 0; i < iFrecuenciaMuestreo * iTiempo; i++) {
                dMuestra = (int) (32767 * Math.sin(2 * Math.PI * iArmonico
                        * ((double) i / iFrecuenciaMuestreo)));
                dMuestra = (~dMuestra) + 1;
                sMuestra = (short) dMuestra;
                writeWAV.write(sMuestra);
                writeWAV.write(sMuestra >> 8);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////// Getters y Setters /////////////////////////
    public byte[] getRiff() {
        return riff;
    }

    public void setRiff(byte[] nRiff) {
        this.riff = nRiff;
    }

    public int getTamano() {
        return tamanio;
    }

    public void setTamano(int nTamano) {
        this.tamanio = nTamano;
    }

    public byte[] getWave() {
        return wave;
    }

    public void setWave(byte[] nWave) {
        this.wave = nWave;
    }

    public int getFormato() {
        return formato;
    }

    public void setFormato(int nFormato) {
        this.formato = nFormato;
    }

    public short getPcm() {
        return pcm;
    }

    public void setPcm(short nPcm) {
        this.pcm = nPcm;
    }

    public short getCanales() {
        return canales;
    }

    public void setCanales(short nCanales) {
        this.canales = nCanales;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int nFrecuencia) {
        this.frecuencia = nFrecuencia;
    }

    public int getF_muestreo() {
        return fMuestreo;
    }

    public void setF_muestreo(int nFMuestreo) {
        this.fMuestreo = nFMuestreo;
    }

    public short getBytes_m() {
        return bytesM;
    }

    public void setBytes_m(short nBytesM) {
        this.bytesM = nBytesM;
    }

    public short getBits_m() {
        return bitsM;
    }

    public void setBits_m(short nBitsM) {
        this.bitsM = nBitsM;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] nData) {
        this.data = nData;
    }

    public int getBytes_archivo() {
        return bytesArchivo;
    }

    public void setBytes_archivo(int nBytesArchivo) {
        this.bytesArchivo = nBytesArchivo;
    }
}
