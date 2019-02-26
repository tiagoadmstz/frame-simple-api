/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;
import org.apache.pdfbox.io.IOUtils;
import org.bouncycastle.asn1.ASN1InputStream;

/**
 *
 * @author tiago.teixeira
 */
public abstract class FileUtil {

    public static void InputStreamToFile(InputStream input, File file) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(IOUtils.toByteArray(input));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static InputStream fileToInputStream(File file) {
        try {
            return Files.newInputStream(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void BytesToFile(byte[] bytes, File file) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] FileToByte(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File writeFile(String fileNames, List<String> lines) {
        try {
            File file = new File(Utilidades.getCurrentPath().concat("/").concat(fileNames));
            if (!file.exists()) {
                try {
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(writer);
                    lines.forEach(l -> {
                        try {
                            bw.append(l);
                            bw.newLine();
                        } catch (Exception ex) {
                        }
                    });
                    bw.close();
                    writer.close();
                } catch (Exception e) {
                }
            }
            return file;
        } catch (Exception e) {
            return null;
        }
    }

}
