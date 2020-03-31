/**
 * 产品数量选择
 */
$(function () {
    // 增加减少商品量
    var goodNum = $(".good-num").val();
    $(".goodNumIn").find(".rightBtn").click(function(){
        goodNum++;
        $(".good-num").val(goodNum);
        return goodNum;
    })
    $(".goodNumIn").find(".leftBtn").click(function(){
        goodNum--;
        if(goodNum == 0){
            goodNum = 1;
        }
        $(".good-num").val(goodNum);
        return goodNum;
    })
})
/**
 * 显示详情
 * @param productid
 * @param flag
 */
function getProductDetailForBuyer() {
    alert("getProductDetailForBuyer");
    var request_param_buyer =  $.parseJSON( $.cookie('request_param_buyer'));
    var users = $.parseJSON( $.cookie('users'));
    alert(users.id);
    $.cookie('request_param_buyer',null);
    $.ajax({
        url: load_productDetail_login_buyer_url,
        type: "get",
        data: {
            productId: request_param_buyer.productId,
            queryNoBuy: request_param_buyer.queryNoBuy,
            userId: users.id
        },
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        success: function (result, response) {
            if (result.success == true) {
                var data = result.data;

            } else if (result.success == false) {
                alert("获取失败" + result.message);
            }
        },
        error: function () {
        }
    });
}
