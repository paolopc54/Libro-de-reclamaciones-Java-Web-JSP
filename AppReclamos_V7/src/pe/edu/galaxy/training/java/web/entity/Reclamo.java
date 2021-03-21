package pe.edu.galaxy.training.java.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@NamedStoredProcedureQueries({
	
	@NamedStoredProcedureQuery(
		name = "reclamo.insertar",
		procedureName = "PKG_RECLAMO.SP_INSERTAR",
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "R_ID_RECLAMO", type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "R_DESCRIPCION", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "R_ID_CLIENTE", type = Long.class)
		}
	),
	
	@NamedStoredProcedureQuery(
		name = "reclamo.modificar",
		procedureName = "PKG_RECLAMO.SP_MODIFICAR",
		resultClasses = ReclamoCliente.class,
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "R_ID_RECLAMO", type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "R_ESTADO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "R_SOLUCION", type = String.class)
		}
	),
	
	@NamedStoredProcedureQuery(
			name = "reclamo.buscarXID",
			procedureName = "PKG_RECLAMO.SP_BUSCAR_X_ID",
			resultClasses = ReclamoCliente.class,
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "R_CURSOR", type = void.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "R_ID_RECLAMO", type = Long.class)
			}
		),
	
	
})

@Table(name = "RECLAMO")
@Entity(name = "Reclamo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Reclamo implements Serializable {

	private static final long  serialVersionUID = 1L;
	@Id
	@Column(name = "ID_RECLAMO")
	private Long id_reclamo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "SOLUCION")
	private String solucion;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	
	
}
