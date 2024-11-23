package com.example.text.Services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;




@Service
public class EmailService {
	@Autowired
 	public JavaMailSender sender;
	public String sendMail(String otp,String email) {
//		System.out.println("s");
		try{
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(email);
			msg.setSubject("Verify Your Account with This OTP");
			msg.setText("Hi,\r\n"
					+ "\r\n"
					+ "To ensure the security of your account, please use the following One-Time Password (OTP) to complete your verification process:\r\n"
					+ "\r\n"
					+ "Your OTP:"+otp+" \r\n"
					+ "\r\n"
					+ "This OTP is valid for the next 10 minutes. Please enter it on the required screen to verify your identity and proceed.\r\n"
					+ "\r\n"
					+ "If you did not request this OTP, please ignore this email or contact our support team immediately.\r\n"
					+ "\r\n"
					+ "Thank you for helping us keep your account secure!");
			
		
			sender.send(msg);
			return "Successfully sent mail";
		}
		catch (MailException ex) {
			System.err.println(ex.getMessage());
			return "Sending mail failed";
		}
	}
	
	  


    
 


}