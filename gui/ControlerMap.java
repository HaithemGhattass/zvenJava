/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  zvendelivery.gui;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ControlerMap {
   public static void main(String[] args) {
      // Recipient's email ID needs to be mentioned.
      String to = "haithemghattas@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "fromemail@gmail.com";
      final String username = "haithem.ghattas@esprit.tn";//change accordingly
      final String password = "191JMT1623";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "relay.jangosmtp.net";


      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "25");

      // Get the Session object.
     Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {

         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Congratulations");

         // This mail has 2 part, the BODY and the embedded image
         MimeMultipart multipart = new MimeMultipart("related");

         // first part (the html)
         BodyPart messageBodyPart = new MimeBodyPart();
         String htmlText2 = "<H1>Hello</H1><img src=\"cid:image\">";
         String htmlText="<!DOCTYPE html>\n" +
"<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
"\n" +
"<head>\n" +
"	<title></title>\n" +
"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"	<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
"	<!--[if !mso]><!-->\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Abril+Fatface\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Bitter\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Cabin\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\" type=\"text/css\">\n" +
"	<!--<![endif]-->\n" +
"	<style>\n" +
"		* {\n" +
"			box-sizing: border-box;\n" +
"		}\n" +
"\n" +
"		body {\n" +
"			margin: 0;\n" +
"			padding: 0;\n" +
"		}\n" +
"\n" +
"		a[x-apple-data-detectors] {\n" +
"			color: inherit !important;\n" +
"			text-decoration: inherit !important;\n" +
"		}\n" +
"\n" +
"		#MessageViewBody a {\n" +
"			color: inherit;\n" +
"			text-decoration: none;\n" +
"		}\n" +
"\n" +
"		p {\n" +
"			line-height: inherit\n" +
"		}\n" +
"\n" +
"		@media (max-width:620px) {\n" +
"			.icons-inner {\n" +
"				text-align: center;\n" +
"			}\n" +
"\n" +
"			.icons-inner td {\n" +
"				margin: 0 auto;\n" +
"			}\n" +
"\n" +
"			.row-content {\n" +
"				width: 100% !important;\n" +
"			}\n" +
"\n" +
"			.column .border {\n" +
"				display: none;\n" +
"			}\n" +
"\n" +
"			table {\n" +
"				table-layout: fixed !important;\n" +
"			}\n" +
"\n" +
"			.stack .column {\n" +
"				width: 100%;\n" +
"				display: block;\n" +
"			}\n" +
"		}\n" +
"	</style>\n" +
"</head>\n" +
"\n" +
"<body style=\"background-color: #FFFFFF; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
"	<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF;\">\n" +
"		<tbody>\n" +
"			<tr>\n" +
"				<td>\n" +
"					<table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffcc00;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fefef8; color: #000000; width: 600px;\" width=\"600\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-bottom:30px;padding-top:15px;width:100%;padding-right:0px;padding-left:0px;\">\n" +
"																<div align=\"center\" style=\"line-height:10px\"><img src=\"https://d15k2d11r6t6rl.cloudfront.net/public/users/Integrators/BeeProAgency/792380_776166/logo.png\" style=\"display: block; height: auto; border: 0; width: 162px; max-width: 100%;\" width=\"162\" alt=\"Burger Shop logo\" title=\"Burger Shop logo\"></div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"heading_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-left:10px;padding-right:10px;text-align:center;width:100%;\">\n" +
"																<h1 style=\"margin: 0; color: #191919; direction: ltr; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 46px; font-weight: 400; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0;\"><strong>Congratulations&nbsp;</strong></h1>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"image_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"width:100%;padding-right:0px;padding-left:0px;padding-top:60px;\">\n" +
"																<div align=\"center\" style=\"line-height:10px\"><img src=\"cid:image\" style=\"display: block; height: auto; border: 0; width: 516px; max-width: 100%;\" width=\"516\" alt=\"Image of three burgers\" title=\"Image of three burgers\"></div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-left:10px;padding-right:10px;padding-top:75px;\">\n" +
"																<div style=\"font-family: Arial, sans-serif\">\n" +
"																	<div class=\"txtTinyMce-wrapper\" style=\"font-size: 14px; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; mso-line-height-alt: 16.8px; color: #393d47; line-height: 1.2;\">\n" +
"																		<p style=\"margin: 0; font-size: 14px; text-align: center;\"><span style=\"font-size:24px;\"><span style=\"font-size:24px;\"><strong>We are Happy to </strong></span><span style=\"font-size:24px;\"><strong>inform</strong></span><span style=\"font-size:24px;\"><strong> you that</strong></span></span></p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<div style=\"font-family: Arial, sans-serif\">\n" +
"																	<div class=\"txtTinyMce-wrapper\" style=\"font-size: 14px; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; mso-line-height-alt: 16.8px; color: #393d47; line-height: 1.2;\">\n" +
"																		<p style=\"margin: 0; font-size: 16px; text-align: center;\">Your restaurant esm el restaurant is now verified</p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"					<table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffcc00;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fefef8; color: #000000; width: 600px;\" width=\"600\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 20px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"button_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<div align=\"center\">\n" +
"																	<!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://127.0.0.1:8000/restaurant_details/199\" style=\"height:44px;width:118px;v-text-anchor:middle;\" arcsize=\"10%\" strokeweight=\"0.75pt\" strokecolor=\"#8a3b8f\" fillcolor=\"#ffcc00\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:16px\"><![endif]--><a href=\"http://127.0.0.1:8000/restaurant_details/199\" target=\"_blank\" style=\"text-decoration:none;display:inline-block;color:#ffffff;background-color:#ffcc00;border-radius:4px;width:auto;border-top:1px solid #8a3b8f;border-right:1px solid #8a3b8f;border-bottom:1px solid #8a3b8f;border-left:1px solid #8a3b8f;padding-top:5px;padding-bottom:5px;font-family:Helvetica Neue, Helvetica, Arial, sans-serif;text-align:center;mso-border-alt:none;word-break:keep-all;\"><span style=\"padding-left:20px;padding-right:20px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\">Thank you</span></span></a>\n" +
"																	<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"divider_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"padding-bottom:20px;padding-left:10px;padding-right:10px;padding-top:20px;\">\n" +
"																<div align=\"center\">\n" +
"																	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"90%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"																		<tr>\n" +
"																			<td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 2px solid #E8E8E8;\"><span>&#8202;</span></td>\n" +
"																		</tr>\n" +
"																	</table>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"social_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<table class=\"social-table\" width=\"168px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" align=\"center\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"																	<tr>\n" +
"																		<td style=\"padding:0 5px 0 5px;\"><a href=\"https://www.facebook.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-only-logo-default-gray/facebook@2x.png\" width=\"32\" height=\"32\" alt=\"Facebook\" title=\"facebook\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																		<td style=\"padding:0 5px 0 5px;\"><a href=\"https://www.twitter.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-only-logo-default-gray/twitter@2x.png\" width=\"32\" height=\"32\" alt=\"Twitter\" title=\"twitter\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																		<td style=\"padding:0 5px 0 5px;\"><a href=\"https://www.linkedin.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-only-logo-default-gray/linkedin@2x.png\" width=\"32\" height=\"32\" alt=\"Linkedin\" title=\"linkedin\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																		<td style=\"padding:0 5px 0 5px;\"><a href=\"https://www.instagram.com/\" target=\"_blank\"><img src=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-only-logo-default-gray/instagram@2x.png\" width=\"32\" height=\"32\" alt=\"Instagram\" title=\"instagram\" style=\"display: block; height: auto; border: 0;\"></a></td>\n" +
"																	</tr>\n" +
"																</table>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"													<table class=\"text_block\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
"														<tr>\n" +
"															<td>\n" +
"																<div style=\"font-family: sans-serif\">\n" +
"																	<div class=\"txtTinyMce-wrapper\" style=\"color: #C0C0C0; font-size: 12px; mso-line-height-alt: 14.399999999999999px; line-height: 1.2; font-family: Helvetica Neue, Helvetica, Arial, sans-serif;\">\n" +
"																		<p style=\"margin: 0; font-size: 12px; text-align: center;\"><span style=\"color:#C0C0C0;\">Copyright © 2022 Zvenn Delivery&nbsp;</span></p>\n" +
"																		<p style=\"margin: 0; font-size: 12px; text-align: center; mso-line-height-alt: 14.399999999999999px;\">&nbsp;</p>\n" +
"																		<p style=\"margin: 0; font-size: 12px; text-align: center;\">Esprit cite el ghazela</p>\n" +
"																	</div>\n" +
"																</div>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"					<table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"						<tbody>\n" +
"							<tr>\n" +
"								<td>\n" +
"									<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 600px;\" width=\"600\">\n" +
"										<tbody>\n" +
"											<tr>\n" +
"												<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
"													<table class=\"icons_block\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"														<tr>\n" +
"															<td style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
"																<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
"																	<tr>\n" +
"																		<td style=\"vertical-align: middle; text-align: center;\">\n" +
"																			<!--[if vml]><table align=\"left\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
"																			<!--[if !vml]><!-->\n" +
"																			<table class=\"icons-inner\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
"																				<!--<![endif]-->\n" +
"																				<tr>\n" +
"																					<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"https://www.designedwithbee.com/?utm_source=editor&amp;utm_medium=bee_pro&amp;utm_campaign=free_footer_link\" target=\"_blank\" style=\"text-decoration: none;\"><img class=\"icon\" alt=\"Designed with BEE\" src=\"https://d15k2d11r6t6rl.cloudfront.net/public/users/Integrators/BeeProAgency/53601_510656/Signature/bee.png\" height=\"32\" width=\"34\" align=\"center\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\"></a></td>\n" +
"																					<td style=\"font-family: Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 15px; color: #9d9d9d; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"https://www.designedwithbee.com/?utm_source=editor&amp;utm_medium=bee_pro&amp;utm_campaign=free_footer_link\" target=\"_blank\" style=\"color: #9d9d9d; text-decoration: none;\">Designed with BEE</a></td>\n" +
"																				</tr>\n" +
"																			</table>\n" +
"																		</td>\n" +
"																	</tr>\n" +
"																</table>\n" +
"															</td>\n" +
"														</tr>\n" +
"													</table>\n" +
"												</td>\n" +
"											</tr>\n" +
"										</tbody>\n" +
"									</table>\n" +
"								</td>\n" +
"							</tr>\n" +
"						</tbody>\n" +
"					</table>\n" +
"				</td>\n" +
"			</tr>\n" +
"		</tbody>\n" +
"	</table><!-- End -->\n" +
"</body>\n" +
"\n" +
"</html>";
         messageBodyPart.setContent(htmlText, "text/html");
         // add it
         multipart.addBodyPart(messageBodyPart);

         // second part (the image)
         messageBodyPart = new MimeBodyPart();
         DataSource fds = new FileDataSource(
            "C:\\wamp64\\www\\WeDev_Zvenn\\public\\frontoffice\\img\\access_bg.jpg");

         messageBodyPart.setDataHandler(new DataHandler(fds));
         messageBodyPart.setHeader("Content-ID", "<image>");

         // add image to the multipart
         multipart.addBodyPart(messageBodyPart);

         // put everything together
         message.setContent(multipart);
         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}