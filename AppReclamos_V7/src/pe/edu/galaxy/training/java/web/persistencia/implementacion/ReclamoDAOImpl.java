package pe.edu.galaxy.training.java.web.persistencia.implementacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.galaxy.training.java.web.entity.Reclamo;
import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ReclamoDAO;

@Repository("reclamoDAO")
@Transactional
public class ReclamoDAOImpl implements ReclamoDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ReclamoCliente buscarXCodigo(Long codigo) throws DAOException {
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("reclamo.buscarXID");

			spq.setParameter("R_ID_RECLAMO",codigo );

			if (spq.execute()) {

				Object ret = spq.getOutputParameterValue("R_CURSOR");

				if (ret != null) {
					
					List<ReclamoCliente> lst=(List<ReclamoCliente>) ret;
					if (lst!=null) {
						return lst.get(0);
					}
					
				}
			}

		} catch (Exception e) {
			System.err.println("Error de consulta " + e.getMessage());
			throw new DAOException(e);
		}
		return null;
	}

	/*
	 * public boolean registroReclamo() { if si lo encontre entonces
	 * actualizar(reclamoCliente) sino registrar(reclamoCliente) agregar() }
	 */
	@Override
	public boolean agregar(ReclamoCliente reclamoCliente) throws DAOException {
		Object id=null;
		boolean sw = false;
		try {
			
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("reclamo.insertar");
			
			spq.setParameter("R_DESCRIPCION", reclamoCliente.getDescripcion());
			spq.setParameter("R_ID_CLIENTE", reclamoCliente.getId_cliente());
			
			spq.execute();
			
			id = spq.getOutputParameterValue(1);
			System.err.println("id reclamo" + id);
			if (id != null) {
				reclamoCliente.setId_reclamo(Long.valueOf(id.toString()));
				sw = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			sw = false;
			throw new DAOException(e);
		}
		return sw;
	}

	@Override
	public boolean modificar(ReclamoCliente reclamoCliente) throws DAOException {
		boolean sw = false;
		
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("reclamo.modificar");
			//IN
			
			spq.setParameter("R_ID_RECLAMO", reclamoCliente.getId_reclamo());
			spq.setParameter("R_ESTADO", reclamoCliente.getEstado());
			spq.setParameter("R_SOLUCION", reclamoCliente.getSolucion());
			
			spq.execute();
			
			sw=true;
		} catch (Exception e) {
			System.out.println("Error al modificar" + e.getMessage());
			throw new DAOException(e);
		}
		
		return sw;
	}

	@Override
	public List<ReclamoCliente> listarXEstado(String estado) throws DAOException {
		
		List<ReclamoCliente> lstreclamoCliente = new ArrayList<>();
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("reclamoCliente.listar");

			spq.setParameter("R_ESTADO", (estado == null ? "" : estado));

			if (spq.execute()) {

				Object ret = spq.getOutputParameterValue("R_CURSOR");

				if (ret != null) {
					lstreclamoCliente = (List<ReclamoCliente>) ret;
				}
			}

		} catch (Exception e) {
			System.err.println("Error de consulta " + e.getMessage());
			throw new DAOException(e);
		}

		return lstreclamoCliente;
	}

}
