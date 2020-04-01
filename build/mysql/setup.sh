#!/bin/bash
set -e

#启动mysql
service mysql start
sleep 3

#导入数据
echo "aaa"
mysql < /mysql/czlshop.sql
sleep 3

#设置mysql密码
mysql < /mysql/privileges.sql

tail -f /dev/null