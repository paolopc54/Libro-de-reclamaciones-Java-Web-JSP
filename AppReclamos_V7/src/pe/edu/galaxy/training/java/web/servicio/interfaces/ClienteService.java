package pe.edu.galaxy.training.java.web.servicio.interfaces;

import java.util.List;

import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.servicio.exception.ServiceException;

public interface ClienteService {

	List<Cliente> listarXDocumento(String documento) throws ServiceException;
	
	Cliente buscarXCodigo(Long codigo) throws ServiceException;
	
	boolean agregar(ReclamoCliente reclamoCliente) throws ServiceException;
	
	boolean modificar(ReclamoCliente reclamoCliente) throws ServiceException;
	
	//RECLAMO CLIENTE
	ReclamoCliente buscarXTipodoc_Doc(String tipoDoc, String doc) throws ServiceException;
}
