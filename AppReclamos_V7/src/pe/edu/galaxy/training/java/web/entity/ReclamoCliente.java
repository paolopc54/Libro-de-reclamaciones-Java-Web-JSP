package pe.edu.galaxy.training.java.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedStoredProcedureQueries({
	
	@NamedStoredProcedureQuery(
		name = "reclamoCliente.listar",
		procedureName = "PKG_RECLAMO.SP_LISTAR_X_ESTADO",
		resultClasses = ReclamoCliente.class,
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "R_CURSOR", type = void.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "R_ESTADO", type = String.class)
		}
	)
	
	
})


@Table(name = "V_RECLAMO_CLIENTE")
@Entity(name = "ReclamoCliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReclamoCliente {

	@Id
	@Column(name = "ID_RECLAMO")
	private Long id_reclamo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "SOLUCION")
	private String solucion;
	
	@Column(name = "ID_CLIENTE")
	private Long id_cliente;
	
	@Column(name = "TIPO_DOCUMENTO")
	private String tipo_doc;
	
	@Column(name = "DOCUMENTO")
	private String doc;
	
	@Column(name = "NOMBRES")
	private String nombres;
	
	@Column(name = "APELLIDO_PATERNO")
	private String apellido_paterno;
	
	@Column(name = "APELLIDO_MATERNO")
	private String apellido_materno;
	
	@Column(name = "DOMICILIO")
	private String domicilio;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "EMAIL")
	private String email;
	
	
}
