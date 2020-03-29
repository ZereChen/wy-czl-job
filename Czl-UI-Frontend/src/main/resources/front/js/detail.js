// $(function () {
//     // 选择商品颜色
//     $(".color-total").find("li").mouseover(function(){
//         $(this).find(".color-title").show();
//         $(this).click(function(){
//             $(this).addClass("clickactive").siblings().removeClass("clickactive");
//         })
//     }).mouseout(function(){
//         $(this).find(".color-title").hide();
//     })
//
//     // 增加减少商品量
//     var goodNum = $(".good-num").val();
//     $(".goodNumIn").find(".rightBtn").click(function(){
//         goodNum++;
//         $(".good-num").val(goodNum);
//         return goodNum;
//     })
//     $(".goodNumIn").find(".leftBtn").click(function(){
//         goodNum--;
//         if(goodNum == 0){
//             goodNum = 1;
//         }
//         $(".good-num").val(goodNum);
//         return goodNum;
//     })
//
//     // 图片浏览
//     $(".m-detail .goodsale .main-slide .items li").hover(function () {
//         $(".m-detail .goodsale .main-slide #preview span img").attr('src', $(this).children("img")[0].src)
//     },function () {
//
//     })
//
// })