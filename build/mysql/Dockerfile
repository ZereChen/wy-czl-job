FROM mysql:5.7

ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

COPY setup.sh /mysql/setup.sh
COPY czlshop.sql /mysql/czlshop.sql
COPY privileges.sql /mysql/privileges.sql

RUN ["mkdir","/home/mysql"]
CMD ["sh", "/mysql/setup.sh"]