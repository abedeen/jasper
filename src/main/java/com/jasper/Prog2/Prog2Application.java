package com.jasper.Prog2;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Prog2Application {


	public Prog2Application(){
		try {
			List<String> projectReportList = new ArrayList<>();
			JRDataSource dataSource = new JRBeanCollectionDataSource(projectReportList);

			//Resource masterResource = applicationContext.getResource("file:///home/abed/jrxml/aa.jrxml");
			FileInputStream masterResource=new FileInputStream(new File("/home/abed/jrxml/aa.jrxml"));
			JasperReport masterReport = JasperCompileManager.compileReport(masterResource);
			Map<String, Object> params = new HashMap<>();
			params.put("country","India");
			params.put("name","India1");

			JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, params, new  JREmptyDataSource());

			File projectReportFile = new File("/home/abed/jrxml/a3.pdf");
			FileOutputStream fileOutputStream = new FileOutputStream(projectReportFile);
			JasperExportManager.exportReportToPdfStream(jasperPrint, fileOutputStream);
			fileOutputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(Prog2Application.class, args);
	}

}
