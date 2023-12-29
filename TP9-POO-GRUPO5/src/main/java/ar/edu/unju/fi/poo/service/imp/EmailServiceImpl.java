package ar.edu.unju.fi.poo.service.imp;


import java.util.Date;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.util.EmailFormat;

@Service
public class EmailServiceImpl  {

	static final Logger logger = Logger.getLogger(ExcelServiceImp.class);
	
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private EmailFormat emailFormat;
    
    public void sendSimpleMessage (String to, String nombre,String contrasenia) throws MessagingException{
      
    	
        MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		message.setTo(to);
		message.setFrom("bancojujuy@outlook.com");
		message.setSubject("Mensaje de Correo Electronico");
		message.setSentDate(new Date());
		message.setText(emailFormat.formatoEmail(nombre, contrasenia),true);
		logger.info("Se ha envido el mail de bienvenida con la contraseña del cliente" + nombre );
		
		this.emailSender.send(mimeMessage);
        
    }
}
