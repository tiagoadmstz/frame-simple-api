/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import org.apache.pdfbox.io.IOUtils;

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

}
