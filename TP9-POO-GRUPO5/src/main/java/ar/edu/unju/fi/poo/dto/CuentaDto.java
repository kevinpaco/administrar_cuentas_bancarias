package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CuentaDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private int numeroCuenta;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fechaIngreso;
	
	private double saldoActual;
	
	private String estado;
	
	private double limiteExtraccion;
	
	private List<MovimientoDto> movimientos=new ArrayList<MovimientoDto>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getLimiteExtraccion() {
		return limiteExtraccion;
	}

	public void setLimiteExtraccion(double limiteExtraccion) {
		this.limiteExtraccion = limiteExtraccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<MovimientoDto> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoDto> movimientos) {
		this.movimientos = movimientos;
	}


}

