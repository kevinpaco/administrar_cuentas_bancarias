package ar.edu.unju.fi.poo.util;

import java.io.File; 

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import ar.edu.unju.fi.poo.service.imp.ExcelServiceImp;


@Component
public class SaldoPdf {

	static final Logger logger = Logger.getLogger(SaldoPdf.class);
	
	public static final String IMAGEN_BANCO= "banco.png";
	private static long nextId = 0;
	
	public static void generarPdf(String nombre, long cuentaNum, long clienteNum, LocalDate fechaIngreso, String estado,
			Double saldo) throws MalformedURLException {
		File file = new File(nombre+"Pdf"+LocalDate.now()+ (nextId++) +".pdf");
		PdfWriter pdfWriter;
		try {
			pdfWriter = new PdfWriter(file);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			pdfDocument.addNewPage();
			pdfDocument.setDefaultPageSize(PageSize.TABLOID);
			Document document = new Document(pdfDocument);
			document.add(titulo());
			document.add(cuerpo(cuentaNum, clienteNum));
			document.add(tablaDeCliente(nombre, fechaIngreso, estado, saldo));
			document.close();
			pdfDocument.close();
			logger.info("Se envio el resumen de saldo del cliente " + nombre + " con nummero de cuenta" + cuentaNum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Paragraph titulo() throws MalformedURLException{
		
		Image bancoImagen = new Image(ImageDataFactory.create(IMAGEN_BANCO));
		bancoImagen.setHeight(110);
		bancoImagen.setWidth(300);
		
		Paragraph titulo = new Paragraph().add(bancoImagen);
		titulo.setFontSize(20);
		titulo.setBold();
	    titulo.setItalic();
		titulo.setBold();
		titulo.setWidth(310);
		titulo.setHeight(120);
		titulo.setTextAlignment(TextAlignment.CENTER);
		titulo.setHorizontalAlignment(HorizontalAlignment.CENTER);
		return titulo;
	}

	private static Paragraph cuerpo(long cuentaNum, long cuentaCli){
    
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yy, hh:mm:ss");		
	LocalDateTime fechaActual = LocalDateTime.now();	
	
	Paragraph cuerpo = new Paragraph("Cuenta Nro: " + cuentaNum + "                                 Fecha y Hora: "
				+ formatoFecha.format(fechaActual) + "\n" + " Numero Cliente: " + cuentaCli + "\n");

		cuerpo.setFontSize(12);
		cuerpo.setBold();
		cuerpo.setBold();
		cuerpo.setWidth(400);
		cuerpo.setHeight(50);
		cuerpo.setTextAlignment(TextAlignment.LEFT);
		cuerpo.setHorizontalAlignment(HorizontalAlignment.CENTER);

		Paragraph espacio = new Paragraph(" ");
		cuerpo.add(espacio);
		return cuerpo;
	}
	
	private static Table tablaDeCliente(String nombre,LocalDate fechaIngreso,String estado, double saldo) {
		
		DecimalFormat df = new DecimalFormat("#.00");
		DateTimeFormatter fechaFormato =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 
		Table tabla= new Table(4);
		
		Cell encabezado= new Cell();
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		encabezado.add(new Paragraph("Nombre y Apellido"));
		tabla.addCell(encabezado);
		
		encabezado=new Cell().add(new Paragraph("Fecha de Ingreso"));
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		tabla.addCell(encabezado);
		
		encabezado=new Cell().add(new Paragraph("Estado"));
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		tabla.addCell(encabezado);
		
		encabezado=new Cell().add(new Paragraph("Saldo Actual"));
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		tabla.addCell(encabezado);
		
		Cell fila= new Cell();
		fila.add(new Paragraph(nombre)).setBorder(Border.NO_BORDER);
		tabla.addCell(fila);
		
		
		fila=new Cell().add(new Paragraph(fechaFormato.format(fechaIngreso))).setBorder(Border.NO_BORDER);
		tabla.addCell(fila);
		
		fila=new Cell().add(new Paragraph(estado)).setBorder(Border.NO_BORDER);
		tabla.addCell(fila);
		
		fila=new Cell().add(new Paragraph("$ "+df.format(saldo))).setBorder(Border.NO_BORDER);
		tabla.addCell(fila);
		tabla.setHorizontalAlignment(HorizontalAlignment.CENTER);
		return tabla;
	}
}
