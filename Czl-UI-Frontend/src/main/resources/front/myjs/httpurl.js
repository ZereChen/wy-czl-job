var host = "172.18.0.7:8080"
var project ="czl-controller"
var base = "http://"+host+"/"+project;
var login_url = base+"/login";
var logout_url = base +"/logout"
var load_productList_nologin_url = base +"/product/query"
var load_productList_login_buyer_url = base +"/product/buyerQuery"
var load_productList_login_seller_url = base +"/product/sellerQuery"
var load_productDetail_login_buyer_url = base+"/product/buyerQueryDetail";

var delete_product_url = base+"/product/delete"
var delete_car_url = base+"/car/delete"
var load_carList_url = base +"/car/query"