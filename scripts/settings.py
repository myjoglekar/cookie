#!/usr/bin/env python
"""
        Settings for Cookie project
        Author: Jp (ootyjp@gmail.com)
        Date: Nov 17. 2016.
"""

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG=True

import os
PATH=os.path.dirname(os.path.abspath(__file__))

# Log configuration
import glob
import logging
import logging.handlers
from logging.handlers import RotatingFileHandler

LOG_FILENAME = 'log/dealer.log'

# Set up a specific logger with our desired output level
app = logging.getLogger('MyLogger')
if DEBUG:
	app.setLevel(logging.DEBUG)

# Add the log message handler to the logger
formatter = logging.Formatter("[%(asctime)s] {%(pathname)s:%(funcName)s:%(lineno)d} %(levelname)s - %(message)s")
handler = logging.handlers.RotatingFileHandler(
              LOG_FILENAME, maxBytes=2000000, backupCount=5)
handler.setFormatter(formatter)
app.addHandler(handler)

app.debug('Logger started.....')

# Email configuration
# Testing uncomment the below lines
# ------------------------------------
# smtp_server = 'gator3272.hostgator.com'
# port = 587
# user_name = 'jp@digitalanalystteam.com'
# password = 'd@tjp527'
# from_address = 'jp@digitalanalystteam.com'
# ----------------------------------------

smtp_server = 'asp.reflexion.net'
port = 25
# user_name = 'jp@digitalanalystteam.com'
# password = 'd@tjp527'
# from_address = 'jp@digitalanalystteam.com'

# Production uncomment the below lines
# ------------------------------------
# smtp_server = 'mail.l2tmedia.com'
# port = 143
# user_name = 'infotech'
# password = 'it@1212'
# from_address = 'infotech@l2tmedia.com'
# --------------------------------------

# Modify below line as required
subject = 'New Dealer Signup'
message ='Hi $dealer_name$,\n\nWelcome to L2TMedia, Happy business!!\nThanks & Regards\n-L2TMedia.'