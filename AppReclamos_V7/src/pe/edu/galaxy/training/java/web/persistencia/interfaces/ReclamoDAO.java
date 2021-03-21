package pe.edu.galaxy.training.java.web.persistencia.interfaces;

import java.util.List;

import pe.edu.galaxy.training.java.web.entity.Reclamo;
import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;

public interface ReclamoDAO {

	//List<Reclamo> listar() throws DAOException;
	
	//Reclamo buscarXCodigo(Long codigo) throws DAOException;
	
	ReclamoCliente buscarXCodigo(Long codigo) throws DAOException;
	
	boolean agregar(ReclamoCliente reclamoCliente) throws DAOException;
	
	boolean modificar(ReclamoCliente reclamoCliente) throws DAOException;
	
	//PARA EL RECLAMOCLIENTE
	List<ReclamoCliente> listarXEstado(String estado) throws DAOException;
}
