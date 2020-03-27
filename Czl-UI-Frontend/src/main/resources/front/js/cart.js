$(function () {

    // 增加减少商品量
    // var goodNum = $(".good-num").val();
    $(".goodNumIn").find(".rightBtn").click(function(){
        var goodNum = $(this).parent().children(".good-num").val();
        goodNum++;
        $(this).parent().children(".good-num").val(goodNum);
        var $price = $(this).parent().parent().parent().parent().find(".price").html()
        var  subtotal = $price * goodNum

        subtotal = subtotal.toFixed(2)
        $(this).parent().parent().parent().parent().find(".subtotal").html(subtotal)
        totalInit()

        return goodNum;
    });
    $(".goodNumIn").find(".leftBtn").click(function(){
        var goodNum = $(this).parent().children(".good-num").val();
        goodNum--;
        if(goodNum == 0){
            goodNum = 1;
        }
        $(this).parent().children(".good-num").val(goodNum);
        var $price = $(this).parent().parent().parent().parent().find(".price").html()
        var  subtotal = $price * goodNum

        subtotal = subtotal.toFixed(2)
        $(this).parent().parent().parent().parent().find(".subtotal").html(subtotal)
        totalInit()

        return goodNum;
    });


    $(".m-cart-body .bd .shoping-list .item .delete").click(function () {
        $(this).parent().parent().remove()
        totalInit()
    });
    
    $(document).ready(function () {
        subtotalInit()
        totalInit()
    })


    // 全选选中框逻辑
    $("li.item [type=checkbox]").click(function(){
        // 选中的个数与checkbox的个数相同,就让上边的第一个 "全选/全不选" 打钩
        var status = $("li.item [type=checkbox]:checked").length == $("li.item [type=checkbox]").length;
        $(".m-cart-body .hd [type=checkbox]").prop("checked", status)
        $(".m-cart-body .ft [type=checkbox]").prop("checked", status)


    })

    $(".m-cart-body .hd [type=checkbox]").click(function(){

        if($(this).prop("checked") == true){
            $("li.item [type=checkbox]").prop("checked", true);
            $(".m-cart-body .ft [type=checkbox]").prop("checked", true)

        } else {
            $("li.item [type=checkbox]").prop("checked", false);
            $(".m-cart-body .ft [type=checkbox]").prop("checked", false)
        }

    });

    $(".m-cart-body .ft [type=checkbox]").click(function(){

        if($(this).prop("checked") == true){
            $("li.item [type=checkbox]").prop("checked", true);
            $(".m-cart-body .hd [type=checkbox]").prop("checked", true)

        } else {
            $("li.item [type=checkbox]").prop("checked", false);
            $(".m-cart-body .hd [type=checkbox]").prop("checked", false)

        }
    });


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