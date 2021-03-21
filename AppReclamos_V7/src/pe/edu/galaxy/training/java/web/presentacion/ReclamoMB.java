package pe.edu.galaxy.training.java.web.presentacion;

import java.util.List;
import java.util.Properties;


import javax.annotation.PostConstruct;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.ACTUALIZACION;
import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.GRABAR;
import static pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum.EMAIL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.web.entity.Cliente;
import pe.edu.galaxy.training.java.web.entity.Reclamo;
import pe.edu.galaxy.training.java.web.entity.ReclamoCliente;
import pe.edu.galaxy.training.java.web.presentacion.enums.CrudEnum;
import pe.edu.galaxy.training.java.web.servicio.exception.ServiceException;
import pe.edu.galaxy.training.java.web.servicio.interfaces.ClienteService;
import pe.edu.galaxy.training.java.web.servicio.interfaces.ReclamoService;

@Slf4j
@Scope("session")
@Controller(value = "reclamoMB")
@Data
@EqualsAndHashCode(callSuper = false)
public class ReclamoMB extends GenericoMB{

	@Autowired
	private ReclamoService reclamoService;
	
	@Autowired
	private ClienteService clienteService;
	
	private Reclamo reclamo = new Reclamo();
	
	private List<Reclamo> reclamos;
	
	private ReclamoCliente reclamoCliente;
	private List<ReclamoCliente> lstReclamoCliente;
	
	private Cliente cliente;
	
	//private Reclamo reclamo;
	
	public ReclamoMB() {
		
	}
	
	@PostConstruct
	public void init() {
		this.setReclamo(new Reclamo());
		this.setCliente(new Cliente());
		this.setReclamoCliente(new ReclamoCliente());
		this.listar();
	}
	
	public void limpiar() {
		this.setReclamo(new Reclamo());
		this.listar();
	}
	
	public String cancelar() {
		
		this.setReclamo(new Reclamo());
		this.listar();
		return "reclamo_listado";
	}
	
	public String atras() {
		this.setReclamo(new Reclamo());
		this.listar();
		return "reclamo_listado";
	}
	
	public void listar() {
		try {
			lstReclamoCliente = this.getReclamoService().listarXEstado(reclamo.getEstado());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	//CAPTURA EL RECLAMO PARA MANDARLO AL FORMULARIO MODIFICAR RECLAMOS
	public String modificar(ReclamoCliente reclamoCliente) {		
		  try {
		  
		  if (reclamoCliente==null) { 
			  return ""; 
		  } 
		  	ReclamoCliente oReclamoCliente =
		  	this.getReclamoService().buscarXCodigo(reclamoCliente.getId_reclamo());
		  	if(oReclamoCliente!=null) { 
		  		this.setReclamoCliente(oReclamoCliente);
		  		return "reclamo_modificar"; 
		  	}else { 
		  		super.msgCRUDError(ACTUALIZACION); }
		  
		  } catch (Exception e) { 
			  super.msgCRUDError(ACTUALIZACION); 
		  } 
		  return "";
		
	}
	
	//para grabar
	public void grabar() {
		
		try {
			if (this.getReclamoCliente().getId_reclamo()>0) {
				reclamoService.modificar(this.getReclamoCliente());
				//enviarEmail();
					super.msgCRUDExito(ACTUALIZACION);
				
			}else {
				super.msgCRUDError(ACTUALIZACION);
			}
			
		} catch (ServiceException e) {
			super.msgCRUDError(CrudEnum.GRABAR);
		}
				
		
	}
	
	
	//FUNCION PARA MANDAR EL CORREO
	public void enviarEmail() {
		
		String remitente = "librodereclamaciones.javaweb30@gmail.com";
		String clave = "comandosur31950854";
		String destino = this.getReclamoCliente().getEmail();
		String asunto = "Reclamo " + this.getReclamoCliente().getNombres() + " " + this.getReclamoCliente().getApellido_paterno() + " " + this.getReclamoCliente().getApellido_materno();
		String mensaje = this.getReclamoCliente().getSolucion();
		
		//propiedades de la conexion
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.user", "remitente");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.clave", "clave");
		
        //preparamos la sesion
        Session session = Session.getDefaultInstance(props);
        
        //construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        
        try {
        	//remitente
        	message.setFrom(new InternetAddress(remitente));
        	//destino
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
        	//asunto
        	message.setSubject(asunto);
        	//cuerpo del mensaje
        	message.setText(mensaje);
			
        	//lo enviamos
			Transport transport = session.getTransport("smtp");
			transport.connect(remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			//cierre
			transport.close();
			System.out.println("Correo enviado");
		} catch (Exception e) {
			e.printStackTrace();
		}
      
	}
	
}
