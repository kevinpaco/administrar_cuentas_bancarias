package ar.edu.unju.fi.poo.util;

import org.springframework.stereotype.Component;

@Component
public class EmailFormat {

	public static String formatoEmail(String nombre,String contrasenia) {         		  
         return  "<!DOCTYPE html>\n"
	          + "<html>\n"
	          + "<head>\n"
	          + "<style>\n" 
	          
	          
	          + "</style>\n"  
	          + "</head>\n"
	          
	          + "<body>\n"
	          + "<img src=\"https://i.ibb.co/T43z4Rm/bancojujuy.jpg\">\n"
	          + "<h1>"
	          + "Hola " + nombre + " Te saludamos de Banco Jujuy, estas a solo un paso de formar parte de esta familia\n" 
	          + "</h1>"
	          + "<h2>"
	          + "Tu contraseña para entrar al homebanking es: " +  contrasenia + " \n" 
	          + "</h2>"
	          
	          + "</body>\n"
	          + "</html>" ;

	        }
}
	
	


