package pe.edu.galaxy.training.java.web.presentacion;

import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.ACTUALIZACION;
import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.REGISTRO;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.web.entity.Cliente;

import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum;
import pe.edu.galaxy.training.java.web.servicio.exception.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfaces.ClienteService;
import pe.edu.galaxy.training.java.web.servicio.interfaces.ReclamoService;

@Scope("session")
@Controller(value =  "clienteMB")
@Data
@EqualsAndHashCode(callSuper = false)

public class ClienteMB extends GenericoMB{

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ReclamoService reclamoService;
	
	private List<Cliente> lstClientes;
	
	private Cliente cliente;
	
	private ReclamoCliente reclamoCliente = new ReclamoCliente();
	
	public ClienteMB(){
		
	}
	
	@PostConstruct
	public void init() {
		this.setCliente(new Cliente());
		this.listar();
	}
	
	public void cancelar(){
		this.setCliente(new Cliente());
		this.setReclamoCliente(new ReclamoCliente());
	}
	
	public void limpiar() {
		
		this.setCliente(new Cliente());
		this.listar();
	}
	
	public void listar(){
		
		try {
			lstClientes = this.getClienteService().listarXDocumento(cliente.getDoc());
		} catch (Exception e) {
			super.msgCRUDError(CrudEnum.LISTADO);
		}
	}
	
	public ReclamoCliente BuscarCliente() {
		
		try {
		ReclamoCliente oreclamoCliente =  this.getClienteService().buscarXTipodoc_Doc(reclamoCliente.getTipo_doc(),reclamoCliente.getDoc()); 
		if (oreclamoCliente==null) {
			return null;
		}else {
			this.setReclamoCliente(oreclamoCliente);
			return  oreclamoCliente;
		}	
		} catch (Exception e) {
			super.msgCRUDError(CrudEnum.ACTUALIZACION);
			
		}
		return  null;
	}
	
	public void grabar() {
		
		
		try {
			
			/*
			 * if (reclamoCliente==null) { return "ERROR AL REGISTRAR"; } ReclamoCliente
			 * oReclamoCliente = BuscarCliente(); if (oReclamoCliente.getId_cliente()>0) {
			 * this.getClienteService().modificar(this.getReclamoCliente()); }else {
			 * this.getClienteService().agregar(this.getReclamoCliente()); }
			 * this.getReclamoService().agregar(this.getReclamoCliente());
			 */
			 
			if (clienteService.agregar(this.getReclamoCliente())) {
				reclamoService.agregar(this.getReclamoCliente()); 
					super.msgCRUDExito(REGISTRO);
				
			}else {
				super.msgCRUDExito(CrudEnum.REGISTRO);
			}
			  
			 
		} catch (ServiceException e) {
			super.msgCRUDError(CrudEnum.ACTUALIZACION);
			
		}
		
	}
	
	
}
