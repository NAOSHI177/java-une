package preprocesador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class preprocesador_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("entrada.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"));

        String linea;
        boolean enComentarioBloque = false;

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();

            if (enComentarioBloque) {
                if (linea.contains("*/")) {
                    enComentarioBloque = false;
                    linea = linea.substring(linea.indexOf("*/") + 2).trim();
                    if (linea.isEmpty()) {
                        continue;
                    }
                } else {
                    continue;
                }
            }

            if (linea.startsWith("//")) {
                continue;
            }

            if (linea.startsWith("/*")) {
                if (!linea.contains("*/")) {
                    enComentarioBloque = true;
                }
                continue;
            }

            if (linea.contains("//")) {
                linea = linea.substring(0, linea.indexOf("//")).trim();
            }

            if (linea.contains("/*")) {
                int inicio = linea.indexOf("/*");
                int fin = linea.indexOf("*/");

                if (fin != -1 && fin > inicio) {
                    String antes = linea.substring(0, inicio).trim();
                    String despues = linea.substring(fin + 2).trim();
                    linea = (antes + " " + despues).trim();
                } else {
                    linea = linea.substring(0, inicio).trim();
                    enComentarioBloque = true;
                }
            }

            if (!linea.isEmpty()) {
                bw.write(linea);
                bw.newLine();
            }
        }

        br.close();
        bw.close();

        System.out.println("Preprocesamiento completado.");
    }
}