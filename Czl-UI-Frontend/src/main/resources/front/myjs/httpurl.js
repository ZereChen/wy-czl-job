var host = "127.0.0.1:9081"
var project ="czl-controller"
var base = "http://"+host+"/"+project;
var login_url = base+"/login";
var logout_url = base +"/logout"
var load_productList_nologin_url = base +"/product/query"
var delete_car_url = base+"/car/delete"
var load_carList_url = base +"/car/query"