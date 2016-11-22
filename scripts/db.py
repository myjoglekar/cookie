#!/usr/bin/env python
"""
        DB API
        Author: Jp (ootyjp@gmail.com)
        Date: Nov 17. 2016.
"""

from db_utils import *

# ===============================[ Dealer]===============================
def dealer_insert(dealer_name,
	delaer_ref_id,
	website,
	communication_email,
	dealer_address,
	dealer_city,
	dealer_state,
	dealer_zip,
	segment_name,
	timezoneName,
	dealer_group,
	oem):

	connection = get_connection()
	dealer_id = None
	try:
		with connection.cursor() as cursor:
			sql = "INSERT INTO dealer (dealer_name, dealer_ref_id, website, communication_email, dealer_address, dealer_city, dealer_state, dealer_zip, segment_name, timezoneName, dealer_group, oem)"+\
				"VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
			cursor.execute(sql, (dealer_name,
				delaer_ref_id,
				website,
				communication_email,
				dealer_address,
				dealer_city,
				dealer_state,
				dealer_zip,
				segment_name,
				timezoneName,
				dealer_group,
				oem))	
			dealer_id = cursor.lastrowid	
		connection.commit()
	finally:
		connection.close()
	return dealer_id

def get_dealer_id(dealer_ref_id):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "SELECT id FROM dealer WHERE dealer_ref_id=%s"
			cursor.execute(sql, (dealer_ref_id))
			result = cursor.fetchone()
			# print(result)
			return result['id']
	finally:
		connection.close()	

def dealer_update(dealer_name,
	website,
	communication_email,
	dealer_address,
	dealer_city,
	dealer_state,
	dealer_zip,
	segment_name,
	timezoneName,
	dealer_group,
	oem,
	delaer_ref_id,):

	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer SET dealer_name=%s, website=%s, communication_email=%s, dealer_address=%s, dealer_city=%s, dealer_state=%s, dealer_zip=%s, segment_name=%s, timezoneName=%s, dealer_group=%s, oem=%s WHERE dealer_ref_id=%s"
			cursor.execute(sql, (dealer_name,
				website,
				communication_email,
				dealer_address,
				dealer_city,
				dealer_state,
				dealer_zip,
				segment_name,
				timezoneName,
				dealer_group,
				oem,
				delaer_ref_id))	
		connection.commit()
	finally:
		connection.close()

def dealer_fetchone(dealer_ref_id):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			# Read a single record
			sql = "SELECT dealer_name, dealer_ref_id, website, communication_email, dealer_address, dealer_city, dealer_state, dealer_zip, segment_name, timezoneName, dealer_group, oem FROM dealer WHERE dealer_ref_id=%s"
			cursor.execute(sql, (dealer_ref_id))
			result = cursor.fetchone()
			return result
	finally:
		connection.close()

# ===============================[ Dealer Product]===============================
def dealer_product_insert(dealer_id, product_name, budget, start_date, end_date, status):
	connection = get_connection()
	
	dealer_product_id = None
	try:
		with connection.cursor() as cursor:
			sql = "INSERT INTO dealer_product (dealer_id, product_name, budget, start_date, end_date, status) VALUES (%s,%s,%s,%s,%s,%s)"
			cursor.execute(sql, (dealer_id, product_name, budget, start_date, end_date, status))		
			dealer_product_id = cursor.lastrowid
		connection.commit()
	finally:
		connection.close()
	return dealer_product_id

def dealer_product_update():
	pass

def dealer_product_fetchone(dealer_id, product_name):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "SELECT id, dealer_id, product_name, budget, start_date, end_date, status FROM dealer_product WHERE dealer_id=%s AND product_name=%s"
			cursor.execute(sql, (dealer_id, product_name))
			result = cursor.fetchone()
			return result
	finally:
		connection.close()
	
def dealer_product_mark_delete_all(status):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer_product SET status=%s"
			cursor.execute(sql, (status))		
		connection.commit()
	finally:
		connection.close()

def dealer_product_mark_delete_using_dealer_id(status, dealer_id):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer_product SET status=%s WHERE dealer_id=%s"
			# print(sql, status, dealer_id)
			cursor.execute(sql, (status, dealer_id))		
		connection.commit()
	finally:
		connection.close()

def dealer_product_mark_delete_using_id(status, dealer_product_id):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer_product SET status=%s WHERE id=%s"
			# print(sql, status, dealer_product_id)
			cursor.execute(sql, (status, dealer_product_id))		
		connection.commit()
	finally:
		connection.close()

# ===============================[ Dealer Product Service]===============================
def dealer_product_service_insert(dealer_product_id, service_name, service_budget, status):
	connection = get_connection()
	dealer_product_service_id = None
	try:
		with connection.cursor() as cursor:
			sql = "INSERT INTO dealer_product_service (dealer_product_id, service_name, service_budget, status) VALUES (%s,%s,%s,%s)"
			cursor.execute(sql, (dealer_product_id, service_name, service_budget, status))
			dealer_product_service_id = cursor.lastrowid		
		connection.commit()
	finally:
		connection.close()
	return dealer_product_service_id

def dealer_product_service_update():
	pass

def dealer_product_service_mark_delete_using_dealer_product_id(dealer_product_id, status):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer_product_service SET status=%s WHERE dealer_product_id=%s"
			cursor.execute(sql, (status, dealer_product_id))		
		connection.commit()
	finally:
		connection.close()

def dealer_product_service_mark_delete_using_id(status, dealer_product_service_id):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer_product_service SET status=%s WHERE id=%s"
			# print(sql, status, dealer_product_service_id)
			cursor.execute(sql, (status, dealer_product_service_id))		
		connection.commit()
	finally:
		connection.close()

def dealer_product_service_fetchone(dealer_product_id, service_name):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "SELECT id, dealer_product_id, service_name, service_budget, status FROM dealer_product_service WHERE dealer_product_id=%s AND service_name=%s"
			cursor.execute(sql, (dealer_product_id, service_name))
			result = cursor.fetchone()
			return result
	finally:
		connection.close()

# ===============================[ Dealer Product Source]===============================
def dealer_product_source_insert(dealer_product_id, account_id, profile_id, source_name, status):
	connection = get_connection()
	dealer_product_source_id = None
	try:
		with connection.cursor() as cursor:
			sql = "INSERT INTO dealer_product_source (dealer_product_id, account_id, profile_id, source_name, status) VALUES (%s,%s,%s,%s,%s)"
			cursor.execute(sql, (dealer_product_id, account_id, profile_id, source_name, status))
			dealer_product_source_id = cursor.lastrowid		
		connection.commit()
	finally:
		connection.close()
	return dealer_product_source_id

def dealer_product_source_mark_delete_using_dealer_product_id(dealer_product_id, status):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer_product_source set status=%s WHERE dealer_product_id=%s"
			cursor.execute(sql, (status, dealer_product_id))		
		connection.commit()
	finally:
		connection.close()

def dealer_product_source_mark_delete_using_id(status, dealer_product_source_id):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "UPDATE dealer_product_source set status=%s WHERE id=%s"
			# print(sql, status, dealer_product_source_id)
			cursor.execute(sql, (status, dealer_product_source_id))		
		connection.commit()
	finally:
		connection.close()

def dealer_product_source_fetchone(dealer_product_id, source_name):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "SELECT id, dealer_product_id, account_id, profile_id, source_name, status FROM dealer_product_source WHERE dealer_product_id=%s AND source_name=%s"
			cursor.execute(sql, (dealer_product_id, source_name))
			result = cursor.fetchone()
			return result
	finally:
		connection.close()

def dealer_product_source_fetchall(dealer_product_id, source_name):
	connection = get_connection()
	try:
		with connection.cursor() as cursor:
			sql = "SELECT id, dealer_product_id, account_id, profile_id, source_name, status FROM dealer_product_source WHERE dealer_product_id=%s AND source_name=%s"
			cursor.execute(sql, (dealer_product_id, source_name))
			result = cursor.fetchall()
			return result
	finally:
		connection.close()
