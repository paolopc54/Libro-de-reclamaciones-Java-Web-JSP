package pe.edu.galaxy.training.java.web.entity;

import java.io.Serializable;

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
import lombok.ToString;

@NamedStoredProcedureQueries({
	
	@NamedStoredProcedureQuery(
	
		name = "cliente.listarXDocumento",
		procedureName = "PKG_CLIENTE.SP_LISTAR_X_DOCUMENTO",
		resultClasses =	Cliente.class,
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "C_CURSOR", type = void.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_DOCUMENTO", type = String.class)
		}
	),
	
	@NamedStoredProcedureQuery(
		name = "cliente.insertar",
		procedureName = "PKG_CLIENTE.SP_INSERTAR",
		resultClasses =	Cliente.class,
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "C_ID_CLIENTE", type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_TIPO_DOCUMENTO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_DOCUMENTO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_NOMBRES", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_APELLIDO_PATERNO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_APELLIDO_MATERNO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_DOMICILIO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_TELEFONO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_EMAIL", type = String.class),
		}
	),
	
	@NamedStoredProcedureQuery(
			
		name = "cliente.buscarXTipodoc_Doc",
		procedureName = "PKG_CLIENTE.SP_BUSCAR_X_TIPODOC_DOC",
		resultClasses =	Cliente.class,
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "C_CURSOR", type = void.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_TIPO_DOCUMENTO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_DOCUMENTO", type = String.class)
			
		}
	),
	
	@NamedStoredProcedureQuery(
			name = "cliente.actualizar",
			procedureName = "PKG_CLIENTE.SP_ACTUALIZAR",
			resultClasses =	Cliente.class,
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_ID_CLIENTE", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_TIPO_DOCUMENTO", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_DOCUMENTO", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_NOMBRES", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_APELLIDO_PATERNO", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_APELLIDO_MATERNO", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_DOMICILIO", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_TELEFONO", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "C_EMAIL", type = String.class),
			}
		)
	
})

@Table(name = "CLIENTE")
@Entity(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
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
