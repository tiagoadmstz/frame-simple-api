/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.util;

import br.com.fs.api.interfaces.ManipulaFrames;
import br.com.fs.api.interfaces.ListenerPatternAdapter;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import net.sf.jasperreports.components.sort.FieldFilter;
import net.sf.jasperreports.components.sort.FilterTypeBooleanOperatorsEnum;
import net.sf.jasperreports.components.sort.FilterTypeNumericOperatorsEnum;
import net.sf.jasperreports.components.sort.FilterTypeTextOperatorsEnum;
import net.sf.jasperreports.components.sort.FilterTypesEnum;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author tiago.teixeira
 */
public abstract class JasperUtil {

    public static final int TEXT = 0, NUMBER = 1, BOOLEAN = 2;

    public static void imprimirRelatorio(Connection conn, String titulo, Image image, Map parametros, InputStream inputStream) {
        try {
            JasperPrint jpPrint = JasperFillManager.fillReport(inputStream, parametros, conn);
            viewReportFrame(titulo, jpPrint, image);
        } catch (Exception exceptionReport) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório selecionado!", "Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
            exceptionReport.printStackTrace();
            JOptionPane.showMessageDialog(null, exceptionReport.getMessage(), "Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void imprimirRelatorio(InputStream relatorio, String titulo, Image image, Map parametros, List<?> listaResultados) {
        try {
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaResultados);
            JasperPrint jpPrint = JasperFillManager.fillReport(relatorio, parametros, ds);
            viewReportFrame(titulo, jpPrint, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imprimirRelatorioPdfTemp(Connection conn, String tituloArquivo, Map parametros, InputStream inputStream) {
        try {
            String source = System.getProperty("user.dir") + tituloArquivo + ".pdf";
            JasperPrint jpPrint = JasperFillManager.fillReport(inputStream, parametros, conn);
            JasperExportManager.exportReportToPdfFile(jpPrint, source.trim());
            //Runtime.getRuntime().exec("cmd /c start " + source);
            File file = new File(source);
            Desktop.getDesktop().open(file);
            file.deleteOnExit();
        } catch (Exception exceptionReport) {
            JOptionPane.showMessageDialog(null, "Não fio possível gerar o relatório selecionado!", "Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
            exceptionReport.printStackTrace();
        }
    }

    public static Map<String, Object> getReportFilter(String campo, String valor, int tipo) {
        Map<String, Object> mapFilters = new HashMap();
        FieldFilter filter = null;
        switch (tipo) {
            case NUMBER:
                filter = new FieldFilter(campo, valor, valor, FilterTypesEnum.NUMERIC.getName(), FilterTypeNumericOperatorsEnum.EQUALS.toString());
                break;
            case TEXT:
                filter = new FieldFilter(campo, valor, valor, FilterTypesEnum.TEXT.getName(), FilterTypeTextOperatorsEnum.EQUALS.toString());
                break;
            case BOOLEAN:
                filter = new FieldFilter(campo, valor, valor, FilterTypesEnum.BOOLEAN.getName(), FilterTypeBooleanOperatorsEnum.IS_TRUE.toString());
                break;
        }
        mapFilters.put(JRParameter.FILTER, filter);
        return mapFilters;
    }

    private static void viewReportFrame(String titulo, JasperPrint print, Image image) {
        JRViewer viewer = new JRViewer(print);
        ManipulaFrames frameReport = new ManipulaFrames() {
            private static final long serialVersionUID = 3109256773218160485L;
        };
        if (image != null) {
            frameReport.setIconImage(image);
        }
        List<JRSaveContributor> saveContributor = Arrays.asList(viewer.getSaveContributors());
        //saveContributor.stream().map(JRSaveContributor::getDescription).forEach(System.out::println);
        saveContributor.stream().filter(sc -> {
            return !sc.getDescription().equals("PDF (*.pdf)")
            && !sc.getDescription().equals("CSV (*.csv)")
            && !sc.getDescription().equals("XML (*.jrpxml, *.xml)")
            && !sc.getDescription().equals("Single sheet XLS (*.xls)")
            && !sc.getDescription().equals("ODT (*.odt)")
            && !sc.getDescription().equals("DOCX (*.docx)")
            ;
            //return !sc.getDescription().equals("PDF (*.pdf)");
        }).forEach(viewer::removeSaveContributor);
        viewer.registerKeyboardAction(new ListenerPatternAdapter(frameReport), "fechar", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JRViewer.WHEN_IN_FOCUSED_WINDOW);
        viewer.setDoubleBuffered(false);
        frameReport.add(viewer, BorderLayout.CENTER);
        frameReport.setSize(500, 500);
        frameReport.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameReport.setVisible(true);
    }

}
