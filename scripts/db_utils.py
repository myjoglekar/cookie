#!/usr/bin/env python
"""
        Databse util
        Author: Jp (ootyjp@gmail.com)
        Date: Nov 17. 2016.
"""

import pymysql.cursors

def get_connection():
	# Connect to the database
	connection = pymysql.connect(host='localhost', user='root',password='',
		db='wa_python', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)

	return connection

def close(connection):
	connection.close()
