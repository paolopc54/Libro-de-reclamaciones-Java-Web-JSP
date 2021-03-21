package pe.edu.galaxy.training.java.web.presentacion;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.web.entity.Administrador;
import lombok.Data;

@ManagedBean(name="loginMB")
@Data
@SessionScoped
@EqualsAndHashCode(callSuper=false)

public class LoginMB extends GenericoMB{

	private Administrador administrador;
	
	@PostConstruct
	public void init() {
		this.setAdministrador(new Administrador());
		this.getAdministrador().setNombre("Paolo Pisfil Castro");
	}
	
	
	public String validarAcceso() {
		
		if (administrador.getAdministrador().equals("admin") && administrador.getClave().equals("123")){
			return "panel";
		}else {
			super.msgAlerta("Administrador y/o clave incorrecto");
	        
		}
		
		return "";
		
	}
}
