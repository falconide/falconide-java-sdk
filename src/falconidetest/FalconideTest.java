
package falconidetest;
import com.netcore.falconide.Email;
import com.netcore.falconide.Falconide;
import com.netcore.falconide.Falconide.Response;
import com.netcore.falconide.MetaData;
import com.netcore.falconide.Row;
import com.netcore.falconide.Table;
import java.util.concurrent.TimeUnit;
import java.io.File;
public class FalconideTest {
	public static void main(String[] args) {

		Falconide falcon = new Falconide("API_KEY");  //Unique API key 
		//falcon.enableConsoleLogging();
		falcon.disableConsoleLogging();
		Email email = new Email();

		email.setEmailContent("EMAIL_CONTENT");  //Content of the email
		email.setFrom("FROM_ADDRESS");  //FROM ADDRESS like 'Test USER'
		email.setFromName("From Name"); //From name
		email.setReplyToId("REPLY_ID"); //Reply to Address in the mail
		email.setSubject("SUBJECT");    //set subject of the email

		MetaData data = new MetaData();
		data.addCC("abc@xyz.com", "ccAPI"); //CC address
		data.addSubstitue("NAME", "ABS"); //Replaces [%NAME%] attibute in n the html content with ABS  
		Table table = data.createTable("rooms");
		Row e = new Row();
		e.addColumnData("id", "1");
		e.addColumnData("image_1", "http://www.falconide.com/img/asianpaints/thumb-pink_02.jpg");
                e.addColumnData("image_2", "http://www.falconide.com/img/asianpaints/thumb-pink_04.jpg");
                e.addColumnData("image_3", "http://www.falconide.com/img/asianpaints/thumb-pink_06.jpg");
                e.addColumnData("image_4", "http://www.falconide.com/img/asianpaints/thumb-pink_08.jpg");
		table.add(e);
		e = new Row();
                e.addColumnData("id", "2");
		e.addColumnData("image_1", "http://www.falconide.com/img/asianpaints/thumb-grey_02.jpg");
                e.addColumnData("image_2", "http://www.falconide.com/img/asianpaints/thumb-grey_04.jpg");
                e.addColumnData("image_3", "http://www.falconide.com/img/asianpaints/thumb-grey_06.jpg");
                e.addColumnData("image_4", "http://www.falconide.com/img/asianpaints/thumb-grey_08.jpg");
		table.add(e);
		data.setXAPIHeader("API123");//Mentions X-API header mentoned ny the use
		email.addRecipient("def@gmail.com", data);

                //Repeat the steps from line 25 to 44 for another email  
	
		email.enableClickTrack();
		email.enableOpenTrack();;
		email.addFooter();
		email.setTemplateId("2928"); // Mention template id created in the falconide panel
                //Below code sets the timeout 
                falcon.setConnectTimeout(1,TimeUnit.MINUTES );
                falcon.setReadTimeout(1,TimeUnit.MINUTES );
                falcon.setWriteTimeout(1,TimeUnit.MINUTES ); 
		
		try {
			email.addAttachment(new File("test.pdf")); //specify the path for attachemnt
			
			Response response = falcon.send(email);
			if (response.getStatus()) {

				System.out.println("Success-Falcon");
				System.out.println(response.getMessage());
			} else {
				System.out.println(response.getCode() + "::::"
						+ response.getMessage());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	
	}
}