package pe.edu.galaxy.training.java.web.persistencia.interfaces;

import java.util.List;

import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;

public interface ClienteDAO {
	
	List<Cliente> listarXDocumento(String documento) throws DAOException;
	
	Cliente buscarXCodigo(Long codigo) throws DAOException;
	
	boolean agregar(ReclamoCliente reclamoCliente) throws DAOException;
	
	boolean modificar(ReclamoCliente reclamoCliente) throws DAOException;
	
	//RECLAMO CLIENTE
	
	ReclamoCliente buscarXTipodoc_Doc(String tipoDoc, String doc) throws DAOException;
	
}
