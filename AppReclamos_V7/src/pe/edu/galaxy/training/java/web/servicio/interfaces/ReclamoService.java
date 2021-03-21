package pe.edu.galaxy.training.java.web.servicio.interfaces;

import java.util.List;

import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.servicio.exception.ServiceException;

public interface ReclamoService {

	//List<Reclamo> listar() throws ServiceException;
	
	//Reclamo buscarXCodigo(Long codigo) throws ServiceException;
	ReclamoCliente buscarXCodigo(Long codigo) throws ServiceException;
		
	boolean agregar(ReclamoCliente reclamoCliente) throws ServiceException;
		
	boolean modificar(ReclamoCliente reclamoCliente) throws ServiceException;
		
	//PARA EL RECLAMOUSUARIO
	List<ReclamoCliente> listarXEstado(String estado) throws ServiceException;
}
