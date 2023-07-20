/**
 * 
 */
package com.qr.document.confia.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mrivera
 *
 */
@Getter
@Setter
public class Bitacora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean success;
	
	private String message;
	
	private String di;
	
	@JsonAlias(value = "fecha_emision")
	private String fechaEmision;
	
	@JsonAlias(value = "periodo_inicial")
	private String periodoInicial;
	
	@JsonAlias(value = "periodo_final")
	private String periodoFinal;
	
	@JsonAlias(value = "saldo_inicial")
	private String saldoInicial;
	
	@JsonAlias(value = "saldo_actual")
	private String saldoActual;
	
	@JsonAlias(value = "cantidad_periodos")
	private String cantidadPeriodos;
	
	@JsonAlias(value = "promedio_ibc")
	private String promedioIbc;
	
	// Campos para visualizar el qr Carnet
	
	@JsonAlias(value = "nombre_completo")
	private String nombreCompleto;
	
	@JsonAlias(value = "fecha_incorp_sap")
	private String fechaIncorporacionSap;
	
	@JsonAlias(value = "fecha_expedicion")
	private String fechaExpedicion;
	
	@JsonAlias(value = "fecha_expiracion")
	private String fechaExpiracion;
	
	@JsonAlias(value = "categoria_fide")
	private String catFidelizacion;
	
	@JsonAlias(value = "categoria_desc")
	private String catDescripcion;

	@Override
	public String toString() {
		return "Bitacora [success=" + success + ", message=" + message + ", di=" + di + ", fechaEmision=" + fechaEmision
				+ ", periodoInicial=" + periodoInicial + ", periodoFinal=" + periodoFinal + ", saldoInicial="
				+ saldoInicial + ", saldoActual=" + saldoActual + ", cantidadPeriodos=" + cantidadPeriodos
				+ ", promedioIbc=" + promedioIbc + ", nombreCompleto=" + nombreCompleto + ", fechaIncorporacionSap="
				+ fechaIncorporacionSap + ", fechaExpedicion=" + fechaExpedicion + ", fechaExpiracion="
				+ fechaExpiracion + ", catFidelizacion=" + catFidelizacion + ", catDescripcion=" + catDescripcion + "]";
	}

}
