package service;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import Mail.MailHelper;
import entity.Reply;

@Path("/forgotPassword")
public class ForgotPasswordService {
	
	@GET
	@Path("sandEmail")
	public String sandEmail(@QueryParam("email") String email,
			@QueryParam("password")String password) {
		try {
			MailHelper.sendMail(email, "forgotPassword", "password :" + password);
			return Reply.OK_STR;
		} catch (MessagingException e) {
			return Reply.FAIL_STR;
		}
	}
}
