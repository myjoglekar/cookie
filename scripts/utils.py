#!/usr/bin/env python
"""
        Utility functions
        Author: Jp (ootyjp@gmail.com)
        Date: Nov 17. 2016.
"""
# Import smtplib for the actual sending function
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

from settings import *

def send_email(from_address, to_address, subject, body):
 
	msg = MIMEMultipart()
	msg['From'] = from_address 
	msg['To'] = to_address 
	msg['Subject'] = subject 		  
	msg.attach(MIMEText(body, 'plain'))
	app.debug(msg)
	# return 

	print(smtp_server, port) # From Settings
	server = smtplib.SMTP(host=smtp_server, port=port)
	try:
		server.set_debuglevel(True)
		# identify ourselves, prompting server for supported features
		server.ehlo()
		server.starttls()
		server.ehlo()
		# server.login(user_name, password)
		text = msg.as_string()
		server.sendmail(from_address, to_address, text)
		app.debug('* Mail from :'+from_address +' to '+to_address +' subject: '+subject+'\n'+text)
	except Exception as e:
		print(e.__class__.__name__)
		print(e)
	finally:
		server.quit()

send_email('jp@digitalanalystteam.com', 'ootyjp@gmail.com', 'test subject', 'This is a test')