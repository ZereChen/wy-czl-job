use mysql;
select host, user from user;
create user root identified by '123456';

grant all on czlshop.* to root@'%' identified by '123456' with grant option;
flush privileges;