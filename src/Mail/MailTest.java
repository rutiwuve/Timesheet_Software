package Mail;

import javax.mail.MessagingException;

public class MailTest {
	
	
	public static void main(String[]args){
		try {
			MailHelper.sendMail("ruti.oba1@gmail.com", "test", "test");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
