function loadHeaderPage() {
    var users = $.cookie('users');
    console.log(users);
    if(users != undefined && users != null){
        var user = $.parseJSON( $.cookie('users'));
        var role = user.roleEntity;
        if(role.id == '3'){
            //买家
            $(".headerpage").load("header_login_buyer.html");
            $(".rightbuttonpage").load("right_button.html");

        }else if(role.id == '2'){
            //卖家
            $(".headerpage").load("header_login_seller.html");
        }
    }else{//未登录
        $(".headerpage").load("header_nologin.html");
    }
}

function saveInfoToCookie(data,response) {
    $.cookie('users', JSON.stringify(data), { expires: 7});
}
