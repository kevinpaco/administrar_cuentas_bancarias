package ar.edu.unju.fi.poo.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.MovimientoDto;




public class ExcelExportUtils {

	    private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private List<MovimientoDto> listaMovientos;
	    private ClienteDto clienteDto;
	   

	    public ExcelExportUtils(List<MovimientoDto> listaMovientos, ClienteDto clienteDto) {
	        this.listaMovientos = listaMovientos;
	        workbook = new XSSFWorkbook();
	        this.clienteDto = clienteDto;
	    }

	    private void createCell(Row row, int columnCount, Object value, CellStyle style){
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	            
			        if (value instanceof Integer){
			            cell.setCellValue((Integer) value);
			        }else if (value instanceof Double){
			            cell.setCellValue((Double) value);
			        }else if (value instanceof LocalDate){
			          cell.setCellValue((LocalDate) value);			          
			        }else if (value instanceof Long){
			            cell.setCellValue((Long) value);
			        }else {
			            cell.setCellValue((String) value);
			        }
	        cell.setCellStyle(style);
	    }
	    
	    
	    private void createHeaderRow(){
	        sheet   = workbook.createSheet("Informacion Movimientos");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(20);
	        style.setFont(font);
	        style.setAlignment(HorizontalAlignment.CENTER);
	        style.setBorderBottom(BorderStyle.MEDIUM);
	        style.setBorderLeft(BorderStyle.MEDIUM);
	        style.setBorderRight(BorderStyle.MEDIUM);
	        style.setBorderTop(BorderStyle.MEDIUM);
	        style.setAlignment(HorizontalAlignment.CENTER);
	        createCell(row, 0, "Informacion Movimientos", style);
	        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
	        font.setFontHeightInPoints((short) 10);
	        row = sheet.createRow(1);
	        createCell(row, 0,"Nro Cuenta: "+clienteDto.getCuenta().getNumeroCuenta(), style);
	        CellRangeAddress region = new CellRangeAddress(1,1,0,6);
	        RegionUtil.setBorderTop(BorderStyle.MEDIUM, region, sheet);
	        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, sheet);
	        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, region, sheet);
	        RegionUtil.setBorderRight(BorderStyle.MEDIUM, region, sheet);
	        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));   
	        font.setFontHeightInPoints((short) 10);
	        row = sheet.createRow(2); 
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        createCell(row, 0, " Id ", style);
	        createCell(row, 1, " Fecha ", style);
	        createCell(row, 2, "  Hora  " , style);
	        createCell(row, 3, " Importe ", style);
	        createCell(row, 4, " Tipo ", style);
	        createCell(row, 5, "  Cliente  ", style);
	        createCell(row, 6, "  Tipo Cliente  ", style);
	     
	    }

	    private void writeCustomerData(){
	        int rowCount = 3;
	        CellStyle style = workbook.createCellStyle();
	        CellStyle style2 = workbook.createCellStyle();
	        CellStyle style3 = workbook.createCellStyle();
	        style3.setDataFormat(workbook.createDataFormat().getFormat("hh:mm:ss"));
	        style2.setDataFormat(workbook.createDataFormat().getFormat("d/m/yyyy"));
	        style.setBorderBottom(BorderStyle.MEDIUM);
	        style.setBorderLeft(BorderStyle.MEDIUM);
	        style.setBorderRight(BorderStyle.MEDIUM);
	        style.setBorderTop(BorderStyle.MEDIUM);
	        style2.setBorderBottom(BorderStyle.MEDIUM);
	        style2.setBorderLeft(BorderStyle.MEDIUM);
	        style2.setBorderRight(BorderStyle.MEDIUM);
	        style2.setBorderTop(BorderStyle.MEDIUM);
	        style3.setBorderBottom(BorderStyle.MEDIUM);
	        style3.setBorderLeft(BorderStyle.MEDIUM);
	        style3.setBorderRight(BorderStyle.MEDIUM);
	        style3.setBorderTop(BorderStyle.MEDIUM);
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	        style2.setFont(font);
	        style3.setFont(font);
	        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        style2.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
	        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        style3.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
	        style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        for (MovimientoDto movimiento : listaMovientos){
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	            sheet.autoSizeColumn(columnCount);
	            createCell(row, columnCount++, movimiento.getId(), style);
	            createCell(row, columnCount++, movimiento.getFecha(), style2);
	            createCell(row, columnCount++, movimiento.getHora().toString(), style3);
	            createCell(row, columnCount++, movimiento.getImporte(), style);
	            createCell(row, columnCount++, movimiento.getTipo(), style);
	            createCell(row, columnCount++, movimiento.getClienteDto().getNombre(), style);
	            createCell(row, columnCount++, tipoCliente(movimiento.getClienteDto()) ,style);
	        }

	    }
	    
	    private String tipoCliente(ClienteDto clienteDto) {
	    	if (clienteDto.getCuenta() != null)
	    		return "Titular";
	    	else
	    		return "Adherente";
	    }

	    public void exportDataToExcel(HttpServletResponse response) throws IOException {
	        createHeaderRow();
	        writeCustomerData();
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }

}
