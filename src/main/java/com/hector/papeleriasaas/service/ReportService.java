package com.hector.papeleriasaas.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ReportService {

    private ReportService() {

    }

    public static <T> JasperPrint generate(List<T> data, String reportPath, Map<String, Object> params) throws JRException {
        JasperReport report = JasperCompileManager.compileReport(ReportService.class.getResourceAsStream(reportPath));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

        return JasperFillManager.fillReport(report, params, dataSource);
    }

}
