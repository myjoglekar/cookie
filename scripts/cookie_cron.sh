#!/bin/sh
USERNAME=root
PASSWORD=12t5erv3r
HOSTNAME=cookie-prod.c2b59vf2suw7.us-west-2.rds.amazonaws.com
DUMP_DIR=/opt/cookie_dump/
mysqldump -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod > $DUMP_DIR/cookie-`date "+%d-%m-%Y"`.sql
gzip $DUMP_DIR/cookie-`date "+%d-%m-%Y"`.sql
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "update dealer set site_id = id;"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "drop index visit_log_report_visit_id on visit_log_report;"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "insert into visit_log_report select * from visit_log where visit_time < date(sysdate());"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "drop index action_log_report_visit_id on action_log_report;"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "insert into action_log_report select * from action_log where action_time < date(sysdate());"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "delete from visit_plugin_properties;"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "delete from visit_log where visit_time < date(sysdate());"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "delete from action_log where action_time < date(sysdate());"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "create index action_log_report_visit_id on action_log_report (visit_id);"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "create index visit_log_report_visit_id on visit_log_report (visit_id);"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "delete from dealer_report;"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "insert into dealer_report select * from dealer;"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "create index action_log_report_dealer_id on action_log_report (dealer_id);"
mysql -h $HOSTNAME -u $USERNAME -p$PASSWORD cookie_prod -e "create index visit_log_report_dealer_id on visit_log_report (dealer_id);"
echo "Completed"
