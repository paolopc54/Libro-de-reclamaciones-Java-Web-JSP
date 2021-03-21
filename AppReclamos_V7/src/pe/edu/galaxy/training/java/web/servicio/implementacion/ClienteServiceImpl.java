package pe.edu.galaxy.training.java.web.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ClienteDAO;
import pe.edu.galaxy.training.java.web.servicio.exception.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfaces.ClienteService;

@Slf4j
@Data
@Transactional
@Service("clienteService")

public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	@Override
	public List<Cliente> listarXDocumento(String documento) throws ServiceException {
		try {
			return this.getClienteDAO().listarXDocumento(documento);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	/*
	 * public boolean insertar(Cliente cliente) throws ServiceException{ try {
	 * return this.getClienteDAO(). } catch (Exception e) { throw new
	 * ServiceException(e); } }
	 */
	
	

	@Override
	public Cliente buscarXCodigo(Long codigo) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean agregar(ReclamoCliente reclamoCliente) throws ServiceException {
		try {
			return this.getClienteDAO().agregar(reclamoCliente);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean modificar(ReclamoCliente reclamoCliente) throws ServiceException {
		try {
			return this.getClienteDAO().modificar(reclamoCliente);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public ReclamoCliente buscarXTipodoc_Doc(String tipoDoc, String doc) throws ServiceException {
		try {
			return this.getClienteDAO().buscarXTipodoc_Doc(tipoDoc, doc);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
