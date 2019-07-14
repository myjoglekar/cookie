#!/usr/bin/env python
"""
        Get access token
        Get dealer list
        Insert/Update in DB

        Author: Mandar Y. Joglekar
        Date: Jul 15. 2019.
"""

import http.client
import urllib
import urllib.parse
import re
import json
import pprint

from db import *
import datetime
from datetime import datetime

from settings import *
from utils import *

def get_connection(host, port):
	# establish connection with the webpage
	connection = http.client.HTTPConnection(host, port)
	print('connection: ',host, str(port), connection)
	return connection

def get_data(headers, params, method, url, connection):
	# url encode the parameters
	url_params = urllib.parse.urlencode(params)
	app.debug('url_params: '+url_params)

	# send out the POST request
	connection.request(method, url, url_params, headers)

	# response
	response = connection.getresponse()
	app.debug('status: message'+str(response.status)+': '+str(response.msg))

	# analyse the response
	data = response.read()
	return data

def close_connection(connection):
	connection.close()


def dealer_to_str(item):
	list = (item['dealerName'],
	item['dealerId'],
	item['dealerDomain'],
	item['accountManager']['email'],
	item['dealerAddress']['address'],
	item['dealerAddress']['city'],
	item['dealerAddress']['state'],
	item['dealerAddress']['zip'],
	item['segment'],
	item['timeZone'],
	item['dealerGroup'],
	item['oem'])  
	return ':'.join(list)

def product_to_str(product):
	list = (product['name'],
	product['budget'],
	str(product['startDate']),
	str(product['endDate']))
	return ':'.join(list)

def service_to_str(service):
	list = (service['name'], 
	service['budget'])
	return ':'.join(list)

def source_to_str(source):
	list = (source['accountid'],
	source['profileId'],
	source['name'])
	return ':'.join(list)

# To get the access token
connection = get_connection('52.5.156.250', 8080)
headers = {'Content-Type' : 'application/x-www-form-urlencoded'}
params = {'client_id':'f8f06d06436f4104ade219fd7d535654',
	'client_secret':'ba082149c90f41c49e86f4862e22e980',
	'grant_type':'password',
	'scope':'FullControl',
	'password':'admin123',
	'username':'admin',
	}
method='POST'
url = '/QA/oauth/access_token'
data = get_data(headers, params, method, url, connection)
close_connection(connection)

# Accessing the actual URL
json_data= json.loads(data.decode("utf-8")) # need to convert byte to string
access_token = json_data['access_token']
connection = get_connection('52.5.156.250', 8080)
headers = {'Authorization':access_token}
params = {
	'Offset':0,
	'Limit':2000,
	'Fromdate':'2014-08-08',
	'Todate': datetime.today().strftime('%Y-%m-%d') # Current date
	}
method='GET'

# NOTE: it works with full URL and empty params {}
# actual url: /QA/rest/activedealerlist
url = '/QA/rest/activedealerlist?Offset=0&Limit=20&Date='+datetime.today().strftime('%Y-%m-%d')
data = get_data(headers, params, method, url, connection)
json_data = data.decode('utf-8')
result  = json.loads(json_data)

# printing in json format
# app.debug(result)
# pprint.pprint(result)
# app.debug(pprint.pprint(result))

for item in result['items']:
	# Fetch dealer from DB using API->dealerId, if not exists then it is a new record
	record = dealer_fetchone(item['dealerId']) 
	if not record: # INSERT new record
		dealer_id = dealer_insert(item['dealerName'],
			item['dealerId'],
			item['dealerDomain'],
			item['accountManager']['email'],
			item['dealerAddress']['address'],
			item['dealerAddress']['city'],
			item['dealerAddress']['state'],
			item['dealerAddress']['zip'],
			item['segment'],
			item['timeZone'],
			item['dealerGroup'],
			item['oem'])
		app.debug('- [NEW] '+dealer_to_str(item))

		message = message.replace('$dealer_name$', item['dealerName'])
			
		from_mail = 'jp@digitalanalystteam.com'
		# NOTE: Uncomment below for actual communication	
		# from_mail = from_address	
		
		communication_email = 'ootyjp@gmail.com'
		# NOTE: Uncomment below for actual communication
		# communication_email = item['accountManager']['email']

		app.debug('* Mail from :'+from_mail +' to '+communication_email +' subject: '+subject+'\n'+message)
		# send_email(from_mail, communication_email, subject, message)

		# Dealer products 
		for product in item['products']:
			if dealer_id:
				dealer_product_id = dealer_product_insert(dealer_id,
					product['name'],
					float(product['budget']),
					product['startDate'],
					product['endDate'], 
					'Active')
				app.debug('-- '+product_to_str(product))
				# Product services
				for service in product['services']:
					dealer_product_service_id = dealer_product_service_insert(dealer_product_id,
						service['name'], 
						service['budget'],
						'Active')
				app.debug('--- '+service_to_str(service))
				# Product sources
				for source in product['sources']:
					dealer_product_source_id = dealer_product_source_insert(dealer_product_id,
						source['accountid'],
						source['profileId'],
						source['name'],
						'Active')
				app.debug('--- '+source_to_str(source))
	
	else: # Existing dealer record, then do update
		dealer_update(item['dealerName'],
		item['dealerDomain'],
		item['accountManager']['email'],
		item['dealerAddress']['address'],
		item['dealerAddress']['city'],
		item['dealerAddress']['state'],
		item['dealerAddress']['zip'],
		item['segment'],
		item['timeZone'],
		item['dealerGroup'],
		item['oem'],
		item['dealerId'])
		# initally marking Inactive for the dealer products
		dealer_id = get_dealer_id(item['dealerId'])
		dealer_product_mark_delete_using_dealer_id('Inactive', dealer_id)
		app.debug('- [EXISTS] '+dealer_to_str(item))
		# check if exists in API response and also avaibale in DB, then make it Active
		for product in item['products']:
			product_name = product['name']
			row = dealer_product_fetchone(dealer_id, product_name)
			
			dealer_product_id = None
			if row: # available, make it active
				dealer_product_id = row['id']
				dealer_product_mark_delete_using_id('Active', dealer_product_id)
				app.debug('-- [EXISTS] '+product_to_str(product))
			else: # if not availabe in DB, then insert it, because it is a new product
				dealer_product_id = dealer_product_insert(dealer_id,
					product['name'],
					float(product['budget']),
					product['startDate'],
					product['endDate'], 
					'Active')
				app.debug('-- [NEW] '+product_to_str(product))

			# Dealer product service
			if dealer_product_id:
				# initally marking Inactive for dealer product service
				dealer_product_service_mark_delete_using_dealer_product_id(dealer_product_id, 'Inactive')
				# check if exists in API response and also avaibale in DB, then make it Active
				for service in product['services']:
					service_name = service['name']
					row = dealer_product_service_fetchone(dealer_product_id, service_name)
					
					dealer_product_service_id = None
					if row: # available, make it active
						dealer_product_service_id = row['id']
						dealer_product_service_mark_delete_using_id('Active', dealer_product_service_id)
						app.debug('--- [EXISTS] '+service_to_str(service))
					else: # if not availabe in DB, then insert it, because it is a new product service
						dealer_product_service_id = dealer_product_service_insert(dealer_product_id,
						service['name'], 
						service['budget'],
						'Active')
						app.debug('--- [NEW] '+service_to_str(service))

			# Dealer product source
			if dealer_product_id:
				# initally marking Inactive for dealer product source
				dealer_product_source_mark_delete_using_dealer_product_id(dealer_product_id, 'Inactive')
				# check if exists in API response and also avaibale in DB, then make it Active
				for source in product['sources']:
					source_name = source['name']
					row = dealer_product_source_fetchone(dealer_product_id, source_name)

					dealer_product_source_id = None
					if row: # available, make it active			
						dealer_product_source_id = row['id']
						dealer_product_source_mark_delete_using_id('Active', dealer_product_source_id)
						app.debug('--- [EXISTS] '+source_to_str(source))
					else: # if not availabe in DB, then insert it, because it is a new product service
						dealer_product_source_id = dealer_product_source_insert(dealer_product_id,
							source['accountid'],
							source['profileId'],
							source['name'],
							'Active')
						app.debug('--- [NEW] '+source_to_str(source))
	

# ------------- Database Mapping details -----------
# ------------- delaer -----------------------------
# id = <AUTO>
# dealer_name = dealerName 
# delaer_ref_id = dealerId
# email = 
# last_site_visit = 
# site_id = ??
# status = 
# website = dealerDomain
# communication_email = accountManager.email
# active_clients_product_name =
# campaign_launch_time =
# dealer_address = dealerAddress.address
# dealer_city = dealerAddress.city
# dealer_state = dealerAddress.state
# dealer_zip = dealerAddress.zip
# digital_advisor = 
# first_contract_time =
# oemName =
# phone = 
# segment_name = segment
# timezoneName = timeZone
# budget = 
# dealer_group = dealerGroup
# oem = oem

# # ------------ dealer_product - list -------------
# id =
# dealer_id = id
# product_name = prds.name
# budget = prds.budget
# start_date = prds.startDate
# end_date = prds.endDate

# # ------------ delaer_product_service - List -----
# id
# dealer_product_id = id
# service_name = prds.service.name
# service_budget = prds.service.budget

# #-------------- dealer_product_source - List -----
# id
# dealer_product_id = id
# account_id = prds.sources.accountid
# profile_id = prds.sources.profileId 
# source_name = prds.sources.name
