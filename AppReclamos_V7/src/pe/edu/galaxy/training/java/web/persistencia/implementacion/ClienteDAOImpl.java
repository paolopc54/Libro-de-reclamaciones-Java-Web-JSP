package pe.edu.galaxy.training.java.web.persistencia.implementacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import pe.edu.galaxy.training.java.web.entity.Cliente;

import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ClienteDAO;


@Repository
@Transactional
public class ClienteDAOImpl implements ClienteDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Cliente> listarXDocumento(String documento) throws DAOException {
		List<Cliente> lstclientes = new ArrayList<>();
		 try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.listarXDocumento");
			spq.setParameter("C_DOCUMENTO", (documento == null ? "" : documento));
			
			if (spq.execute()) {
				Object ret = spq.getOutputParameterValue("C_CURSOR");
				
				if (ret != null) {
					lstclientes = (List<Cliente>) ret;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error de consulta " + e.getMessage());
			throw new DAOException(e);
		}
		 return lstclientes;
	}
	
	/*
	 * public boolean insertar(Cliente cliente) throws DAOException{ boolean sw =
	 * false;
	 * 
	 * try {
	 * 
	 * StoredProcedureQuery spq =
	 * em.createNamedStoredProcedureQuery("cliente.insertar"); //IN
	 * spq.setParameter("C_TIPO_DOCUMENTO", cliente.getTipo_doc());
	 * spq.setParameter("C_DOCUMENTO", cliente.getDoc());
	 * spq.setParameter("C_NOMBRES", cliente.getNombres());
	 * spq.setParameter("C_APELLIDO_PATERNO", cliente.getApellido_paterno());
	 * spq.setParameter("C_APELLIDO_MATERNO", cliente.getApellido_materno());
	 * spq.setParameter("C_DOMICILIO", cliente.getDomicilio());
	 * spq.setParameter("C_TELEFONO", cliente.getTelefono());
	 * spq.setParameter("C_EMAIL", cliente.getEmail());
	 * 
	 * spq.execute();
	 * 
	 * Object id = spq.getOutputParameterValue(1);//C_ID_CLIENTE if (id !=null) {
	 * cliente.setId_cliente(Long.valueOf(id.toString())); sw = true; } em.close();
	 * 
	 * } catch (Exception e) { throw new DAOException(e); }
	 * 
	 * return sw; }
	 */
	@Override
	public Cliente buscarXCodigo(Long codigo) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean agregar(ReclamoCliente reclamoCliente) throws DAOException {
		boolean sw = false;
		
		try {
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.insertar");
			//IN
			spq.setParameter("C_TIPO_DOCUMENTO", reclamoCliente.getTipo_doc());
			spq.setParameter("C_DOCUMENTO", reclamoCliente.getDoc());
			spq.setParameter("C_NOMBRES", reclamoCliente.getNombres());
			spq.setParameter("C_APELLIDO_PATERNO", reclamoCliente.getApellido_paterno());
			spq.setParameter("C_APELLIDO_MATERNO", reclamoCliente.getApellido_materno());
			spq.setParameter("C_DOMICILIO", reclamoCliente.getDomicilio());
			spq.setParameter("C_TELEFONO", reclamoCliente.getTelefono());
			spq.setParameter("C_EMAIL", reclamoCliente.getEmail());
			
			spq.execute();
			
			Object id = spq.getOutputParameterValue(1);//C_ID_CLIENTE
			if (id !=null) {
				reclamoCliente.setId_cliente(Long.valueOf(id.toString()));
				sw = true;
			}
			
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return sw;
	}

	@Override
	public boolean modificar(ReclamoCliente reclamoCliente) throws DAOException {
		boolean sw = false;
		
		try {
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.actualizar");
			//IN
			spq.setParameter("C_ID_CLIENTE", reclamoCliente.getId_cliente());
			spq.setParameter("C_TIPO_DOCUMENTO", reclamoCliente.getTipo_doc());
			spq.setParameter("C_DOCUMENTO", reclamoCliente.getDoc());
			spq.setParameter("C_NOMBRES", reclamoCliente.getNombres());
			spq.setParameter("C_APELLIDO_PATERNO", reclamoCliente.getApellido_paterno());
			spq.setParameter("C_APELLIDO_MATERNO", reclamoCliente.getApellido_materno());
			spq.setParameter("C_DOMICILIO", reclamoCliente.getDomicilio());
			spq.setParameter("C_TELEFONO", reclamoCliente.getTelefono());
			spq.setParameter("C_EMAIL", reclamoCliente.getEmail());
			
			spq.execute();
			
			sw=true;
			
			
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return sw;
		
	}
	
	@Override
	public ReclamoCliente buscarXTipodoc_Doc(String tipoDoc, String doc) throws DAOException {
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.buscarXTipodoc_Doc");

			spq.setParameter("C_TIPO_DOCUMENTO", tipoDoc);
			spq.setParameter("C_DOCUMENTO", doc );

			if (spq.execute()) {

				Object ret = spq.getOutputParameterValue("C_CURSOR");

				if (ret != null) {										
					return (ReclamoCliente) ret;
					
				}
			}

		} catch (Exception e) {
			System.err.println("Error de consulta " + e.getMessage());
			throw new DAOException(e);
		}
		return null;
	}
	

}
