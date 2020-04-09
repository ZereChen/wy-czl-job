$(function () {
    // 导航栏下拉卡片显示
    $('.nav-item li').mouseenter(function () {
        $(this).children('.nav-card').fadeIn(150,function () {
            $(this).parent().siblings().children('.nav-card').hide(0);
        });

    });
    $('.nav-item').mouseleave(function () {
        $('.nav-card').fadeOut(150);
    });

});

// 导航栏滚动悬浮
$(document).ready(function() {
    $(window).scroll(function () {
        if ($(window).scrollTop() > 200) {
            $('.store-header').addClass('store-header-fixed');
            $('.g-row').addClass('g-row-fixed');
            $('.tab-logo').addClass('tab-logo-fixed');
            $('.nav-item').addClass('nav-item-fixed');
            $('.nav-item .item').addClass('item-fixed');
            $('.nav-card').addClass('nav-card-fixed');
            $('.fixed-hide').hide(0);
            $('.m-search').hide(0);
            $('.notlogin').css('display','block');
            $('.cart-div').addClass('cart-div-fixed');
        }else if($(window).scrollTop() < 200) {
            //$('.back-to-top').fadeOut(0);
            $('.store-header').removeClass('store-header-fixed');
            $('.g-row').removeClass('g-row-fixed');
            $('.tab-logo').removeClass('tab-logo-fixed');
            $('.nav-item').removeClass('nav-item-fixed');
            $('.item').removeClass('item-fixed');
            $('.nav-card').removeClass('nav-card-fixed');
            $('.fixed-hide').show(0);
            $('.m-search').show(0);
            $('.notlogin').css('display','none');
            $('.cart-div').removeClass('cart-div-fixed');
        }
        if ($(window).scrollTop() > 500) {
            $('.back-to-top').fadeIn(100);
        }else{
            $('.back-to-top').fadeOut(100);
        }
    });

});
