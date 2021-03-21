package pe.edu.galaxy.training.java.web.servicio.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.web.entity.Reclamo;
import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ClienteDAO;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ReclamoDAO;
import pe.edu.galaxy.training.java.web.servicio.exception.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfaces.ReclamoService;

@Slf4j
@Data
@Service("reclamoService")
public class ReclamoServiceImpl implements ReclamoService {

	@Autowired
	private ReclamoDAO reclamoDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	public ReclamoServiceImpl() {
		
	}
	
	@Override
	public ReclamoCliente buscarXCodigo(Long codigo) throws ServiceException {
		try {
			
			return this.getReclamoDAO().buscarXCodigo(codigo);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean agregar(ReclamoCliente reclamoCliente) throws ServiceException {
		//boolean sw = false;
		
		try {
			return this.getReclamoDAO().agregar(reclamoCliente);		
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		
		//return sw;
	}

	@Override
	public boolean modificar(ReclamoCliente reclamoCliente) throws ServiceException {
		try {
			
			return this.getReclamoDAO().modificar(reclamoCliente);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ReclamoCliente> listarXEstado(String estado) throws ServiceException {
		try {
			return this.getReclamoDAO().listarXEstado(estado);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
