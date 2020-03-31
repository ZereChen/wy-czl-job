$(function () {

    // 增加减少商品量
    // var goodNum = $(".good-num").val();
    // $(".goodNumIn").find(".rightBtn").click(function(){
    //     var goodNum = $(this).parent().children(".good-num").val();
    //     goodNum++;
    //     $(this).parent().children(".good-num").val(goodNum);
    //     var $price = $(this).parent().parent().parent().parent().find(".price").html()
    //     var  subtotal = $price * goodNum
    //
    //     subtotal = subtotal.toFixed(2)
    //     $(this).parent().parent().parent().parent().find(".subtotal").html(subtotal)
    //     totalInit()
    //
    //     return goodNum;
    // });
    // $(".goodNumIn").find(".leftBtn").click(function(){
    //     var goodNum = $(this).parent().children(".good-num").val();
    //     goodNum--;
    //     if(goodNum == 0){
    //         goodNum = 1;
    //     }
    //     $(this).parent().children(".good-num").val(goodNum);
    //     var $price = $(this).parent().parent().parent().parent().find(".price").html()
    //     var  subtotal = $price * goodNum
    //
    //     subtotal = subtotal.toFixed(2)
    //     $(this).parent().parent().parent().parent().find(".subtotal").html(subtotal)
    //     totalInit()
    //
    //     return goodNum;
    // });


    // $(".m-cart-body .bd .shoping-list .item .delete").click(function () {
    //     $(this).parent().parent().remove()
    //     totalInit()
    // });




    // 全选选中框逻辑
    // $("li.item [type=checkbox]").click(function(){
    //     // 选中的个数与checkbox的个数相同,就让上边的第一个 "全选/全不选" 打钩
    //     var status = $("li.item [type=checkbox]:checked").length == $("li.item [type=checkbox]").length;
    //     $(".m-cart-body .hd [type=checkbox]").prop("checked", status)
    //     $(".m-cart-body .ft [type=checkbox]").prop("checked", status)
    //
    //
    // })

    // $(".m-cart-body .hd [type=checkbox]").click(function(){
    //
    //     if($(this).prop("checked") == true){
    //         $("li.item [type=checkbox]").prop("checked", true);
    //         $(".m-cart-body .ft [type=checkbox]").prop("checked", true)
    //
    //     } else {
    //         $("li.item [type=checkbox]").prop("checked", false);
    //         $(".m-cart-body .ft [type=checkbox]").prop("checked", false)
    //     }
    //
    // });

    // $(".m-cart-body .ft [type=checkbox]").click(function(){
    //
    //     if($(this).prop("checked") == true){
    //         $("li.item [type=checkbox]").prop("checked", true);
    //         $(".m-cart-body .hd [type=checkbox]").prop("checked", true)
    //
    //     } else {
    //         $("li.item [type=checkbox]").prop("checked", false);
    //         $(".m-cart-body .hd [type=checkbox]").prop("checked", false)
    //
    //     }
    // });


});
// 初始化小计金额
function subtotalInit() {
    $(".m-cart-body .bd .shoping-list .item .subtotal").each(function () {
        let price = $(this).parent().parent().find(".price").html()
        // console.log(price)
        let num = $(this).parent().parent().find(".good-num").val()
        // console.log(num)
        let subtotal = price * num

        subtotal = subtotal.toFixed(2)
        $(this).html(subtotal)

    })
}
// 初始化总金额
function totalInit() {
    let total = 0
    $(".m-cart-body .shoping-list .subtotal").each(function () {
        total += Number($(this).html())
    })
    total = total.toFixed(2)
    $(".m-cart-body .ft .total .num").html(total)
}

/**
 * 删除某一个购物车
 * @param carId
 */
function deleteCar(carId) {
    $.ajax({
        url : delete_car_url,
        type : "get",
        data : {
            carId: carId
        },
        dataType : "json",
        xhrFields: {
            withCredentials: true
        },
        success : function (result,response) {
            if (result.success == true){
                window.location.href='cart.html';
            } else if (result.success == false){
                alert(result.message);
            }
        },
        error : function () {
        }
    })
}

/**
 * 查询用户的购物车列表
 */
function loadCarList() {
    var users = $.parseJSON( $.cookie('users'));
    $.ajax({
        url : load_carList_url,
        type : "get",
        data : {
            userId: users.id
        },
        dataType : "json",
        xhrFields: {
            withCredentials: true
        },
        success : function (result,response) {
            if (result.success == true){
                console.log(result.data);

                var num =result.data.length;
                var html_code = "";
                for (var i = 0; i < num; i++) {
                    html_code=html_code+generateCarListItem(result.data[i]);
                }
                $('#czl-car-list-item').html(html_code);
                subtotalInit();
                totalInit();
            } else if (result.success == false){
                alert(result.message);
            }
        },
        error : function () {
        }
    });

    function generateCarListItem(data) {
        var product = data.productEntity;
        var code ="";
        code+= "<li class=\"item\">\n" +
            "                        <div class=\"w w1\"></div>\n" +
            "                        <div class=\"w w2\">\n" +
            "                            <img class=\"left\" src=\""+product.url+"\" alt=\"\">\n" +
            "                            <div class=\"info left\">\n" +
            "                                <span class=\"g-name\"><a href=\"\">"+product.name+"</a></span>\n" +
            "                                <span class=\"g-attr\"></span>\n" +
            "                                <span class=\"sign\">";
        if(product.prodStateEnum=="CLOSE"){
            code+="已下架";
        }
        code+="</span>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <div class=\"w w3\">\n" +
            "                            <span>¥</span><div class=\"price\">"+product.price+"</div>\n" +
            "                        </div>\n" +
            "                        <div class=\"w w4\">\n" +
            "                            <div class=\"goodNum clearfix\">\n" +
            "                                <div class=\"left goodNumIn\">\n" +
            "                                    <input type=\"text\" value='"+data.num+"'  class=\"left good-num\" readonly/>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <div class=\"w w5\">\n" +
            "                            <span>¥</span><div class=\"subtotal\">0.00</div>\n" +
            "                        </div>\n" +
            "                        <div class=\"w w6\">\n" +
            "                            <a class=\"delete\" onclick=\"deleteCar('"+data.id+"')\">删除</a>\n" +
            "                        </div>\n" +
            "                    </li>";
        return code;
    };
}
