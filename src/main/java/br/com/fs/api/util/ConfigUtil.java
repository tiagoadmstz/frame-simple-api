/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Tiago
 */
public final class ConfigUtil {

    private final static List<String> FLAGS = Arrays.asList("icon_img", "logo_img", "splash_img", "report_img", "system_title");

    public static Image getImageIconFile() {
        return getImage("icon_img", "/br/com/fs/api/img/icone.gif");
    }

    public static Image getImageLogoFile() {
        return getImage("logo_img", "/br/com/fs/api/img/logox.png");
    }

    public static Image getImageSplashFile() {
        return getImage("splash_img", "/br/com/fs/api/img/splash.gif");
    }

    public static Image getImageReport() {
        return getImage("report_img", "/br/com/fs/api/img/report.gif");
    }

    public static String getSystemTitle() {
        return getValues("system_title");
    }

    private static String getValues(String flag) {
        try {
            File file = getConfigFile();
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            while (br.ready()) {
                String line = br.readLine();
                if (line.contains(flag)) {
                    String[] values = line.split("::");
                    if (values.length > 1) {
                        if (!values[1].equals("")) {
                            return values[1].trim();
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Image getImage(String flag, String padrao) {
        try {
            String string = getValues(flag);
            if (string != null) {
                return new ImageIcon(getValues(flag)).getImage();
            } else {
                return new ImageIcon(new ConfigUtil().getResource(padrao)).getImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File getConfigFile() {
        try {
            File file = new File(Utilidades.getCurrentPath().concat("/project_conf.inf"));
            if (!file.exists()) {
                try {
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(writer);
                    FLAGS.forEach(f -> {
                        try {
                            bw.append(f);
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

    private URL getResource(String path) {
        try {
            return getClass().getResource(path);
        } catch (Exception e) {
            return null;
        }
    }

}
