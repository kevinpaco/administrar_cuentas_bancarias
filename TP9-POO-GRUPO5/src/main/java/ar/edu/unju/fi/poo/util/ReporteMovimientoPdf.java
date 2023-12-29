package ar.edu.unju.fi.poo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

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

import ar.edu.unju.fi.poo.model.Cliente;
import ar.edu.unju.fi.poo.model.Movimiento;

public class ReporteMovimientoPdf {
	
	static final Logger logger = Logger.getLogger(ReporteMovimientoPdf.class);
	
	public static final String IMAGEN_BANCO= "banco.png";
	private static long nextId = 0;
	
	public static void generarPdf(Cliente cliente) throws MalformedURLException {
		File file = new File(cliente.getNombre()+"Pdf"+LocalDate.now()+ (nextId++) +".pdf");
		PdfWriter pdfWriter;
		try {
			pdfWriter = new PdfWriter(file);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			pdfDocument.addNewPage();
			pdfDocument.setDefaultPageSize(PageSize.TABLOID);
			Document document = new Document(pdfDocument);
			document.add(titulo());
		  	document.add(cuerpo(cliente.getNombre(),cliente.getDni()));
			document.add(tablaDeCliente(cliente));
			document.close();
			pdfDocument.close();
			logger.info("Se exportaron los ultimos 20 movimentos (máximo) del cliente" +cliente.getId());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
// ESTA BIEN
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
//
	private static Paragraph cuerpo(String nombre, long dni){
    
	Paragraph cuerpo = new Paragraph("Nombre del Cliente/Adherente: " + nombre + "                                 Dni: "
				+ dni + "\n" );

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
	
	private static Table tablaDeCliente(Cliente cliente) {
		
		DecimalFormat df = new DecimalFormat("#.00");
		DateTimeFormatter fechaFormato =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 
		Table tabla= new Table(4);
		
		Cell encabezado= new Cell();
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		encabezado.add(new Paragraph("Fecha"));
		tabla.addCell(encabezado);
		encabezado=new Cell().add(new Paragraph("Tipo"));
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		tabla.addCell(encabezado);
		
		encabezado=new Cell().add(new Paragraph("Importe"));
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		tabla.addCell(encabezado);
		
		encabezado=new Cell().add(new Paragraph("Numero De cuenta"));
		encabezado.setBackgroundColor(new DeviceRgb(0,0,250));
		tabla.addCell(encabezado);
		
		for(int i = cliente.getMovimientos().size()-1 ; i>=0 && i!=cliente.getMovimientos().size()-21;i--)
		{
			tabla.addCell(new Paragraph(fechaFormato.format(cliente.getMovimientos().get(i).getFecha()))).setBorder(Border.NO_BORDER);
			tabla.addCell(new Paragraph( (cliente.getMovimientos().get(i).getTipo()))).setBorder(Border.NO_BORDER);
			tabla.addCell(new Paragraph("$ "+df.format(cliente.getMovimientos().get(i).getImporte()))).setBorder(Border.NO_BORDER);
			tabla.addCell(new Paragraph( ""+cliente.getMovimientos().get(i).getCuenta().getNumeroCuenta() )).setBorder(Border.NO_BORDER);
			tabla.setHorizontalAlignment(HorizontalAlignment.CENTER);
		}
		/*
		for(Movimiento movimiento: cliente.getMovimientos())
		{ 
			tabla.addCell(new Paragraph(fechaFormato.format(movimiento.getFecha()))).setBorder(Border.NO_BORDER);
			tabla.addCell(new Paragraph( (movimiento.getTipo()))).setBorder(Border.NO_BORDER);
			tabla.addCell(new Paragraph("$ "+df.format(movimiento.getImporte()))).setBorder(Border.NO_BORDER);
			tabla.addCell(new Paragraph( ""+( movimiento.getCuenta().getNumeroCuenta()))).setBorder(Border.NO_BORDER);
			tabla.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
		}*/
		return tabla;
	}
	
	
}
