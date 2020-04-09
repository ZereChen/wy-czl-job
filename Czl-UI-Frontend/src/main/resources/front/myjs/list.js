/**
 * 未登录用户显示全部产品列表
 * @param numPerPage 每一页多少条数据
 * @param curPage 当前第几页
 */
function loadProductListNologin(numPerPage,curPage) {
    $.ajax({
        url : load_productList_nologin_url,
        type : "get",
        data : {
            prodStateCode: '1',
            numPerPage: numPerPage,
            page:curPage,
        },
        dataType : "json",
        success : function (result,response) {
            if (result.success == true){
                var num =result.data.length;
                var row_num = Math.ceil(num/4);//多少行数
                var column_num = num%4;//最后一列数量
                if(column_num == 0) row_num+=1;//修正满格情况
                var html_code = "";
                for (var i = 1; i <= row_num; i++) {
                    html_code=html_code+"<ul class=\"m-item-list\">";
                    if(i == row_num){
                        for(var j=0;j<column_num;j++){
                            html_code=html_code+generateli(result.data[(i-1)*4+j]);
                        }
                    }else{
                        for(var j=0;j<4;j++){
                            html_code=html_code+generateli(result.data[(i-1)*4+j]);
                        }
                    }
                    html_code=html_code+"</ul>";
                }
                $('#bdclearfix_nologin').html(html_code);
                $('.M-box11').pagination({
                    pageCount:result.pages,
                    current: curPage,
                    jump: true,
                    callback:function (api) {
                        loadProductListNologin(numPerPage,api.getCurrent());
                    }
                });
            } else if (result.success == false){
                alert("获取失败"+result.message);
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
    function generateli(data) {
       return  " <li class=\"il-item\">\n" +
        "                        <div class=\"product\">\n" +
        "                            <div class=\"product-hd\">\n" +
        "                                <a href=\"login.html\">\n" +
        "                                    <img src=\""+data.url+"\" alt=\"\" class=\"product-img\">\n" +
        "                                    <p class=\"new-tag hide\"></p>\n" +
        "                                </a>\n" +
        "                            </div>\n" +
        "                            <div class=\"product-bd\">\n" +
        "                                <div class=\"pro-name\">\n" +
        "                                    <a href=\"\" class=\"product-name\"> "+data.name+"</a>\n" +
        "                                </div>\n" +
        "                                <p class=\"price\">\n" +
        "                                    <span>¥"+data.price+"</span>\n" +
        "                                </p>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </li>"
    };
}


/**
 * 买家显示产品列表
 * @param numPerPage
 * @param curPage
 * @param queryNoBuy false显示全部，true显示未购买
 */
function loadProductListloginForBuyer(numPerPage,curPage,queryNoBuy) {
    var users =  $.parseJSON( $.cookie('users'));
    $.ajax({
        url: load_productList_login_buyer_url,
        type: "get",
        data: {
            prodStateCode: '1',
            numPerPage: numPerPage,
            page: curPage,
            userId: users.id,
            queryNoBuy: queryNoBuy
        },
        headers:{
            Accept: "application/json; charset=utf-8",
            "x-token": localStorage.getItem("token")
        },
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        success: function (result, response) {
            if (result.success == true) {
                var num = result.data.length;
                var row_num = Math.ceil(num / 4);//多少行数
                var column_num = num % 4;//最后一列数量
                if (column_num == 0) row_num += 1;//修正满格情况
                var html_code = "";
                for (var i = 1; i <= row_num; i++) {
                    html_code = html_code + "<ul class=\"m-item-list\">";
                    if (i == row_num) {
                        for (var j = 0; j < column_num; j++) {
                            html_code = html_code + generateli(result.data[(i - 1) * 4 + j]);
                        }
                    } else {
                        for (var j = 0; j < 4; j++) {
                            html_code = html_code + generateli(result.data[(i - 1) * 4 + j]);
                        }
                    }
                    html_code = html_code + "</ul>";
                }
                $('#bdclearfix_login_buyer').html(html_code);
                $('.M-box11').pagination({
                    pageCount: result.pages,
                    current: curPage,
                    jump: true,
                    callback: function (api) {
                        loadProductListloginForBuyer(numPerPage, api.getCurrent(),queryNoBuy);
                    }
                });
            } else if (result.success == false) {
                alert("获取失败" + result.message);
            }
        },
        error: function () {
        }
    });
    function generateli(data) {
        var code = "";
        code = " <li class=\"il-item\">\n" +
            "                        <div class=\"product\">\n" +
            "                            <div class=\"product-hd\">\n";
        if(data.buyed==true){
            code+="<a onclick=\"getProductDetailForBuyerJump('"+data.id+"',true)\">\n" +
                "                                    <img src=\""+data.url+"\" alt=\"\" class=\"product-img\">\n" +
                "                                    <p class=\"new-tag\">已购买</p>\n" +
                "                                </a>";
        }else{
            code+="<a onclick=\"getProductDetailForBuyerJump('"+data.id+"',false)\">\n" +
                "                                    <img src=\""+data.url+"\" alt=\"\" class=\"product-img\">\n" +
                "                                    <p class=\"new-tag hide\"></p>\n" +
                "                                </a>";
        }
        code+="</div>\n" +
            "                            <div class=\"product-bd\">\n" +
            "                                <div class=\"pro-name\">\n" +
            "                                    <a href=\"\" class=\"product-name\"> "+data.name+"</a>\n" +
            "                                </div>\n" +
            "                                <p class=\"price\">\n" +
            "                                    <span>¥"+data.price+"</span>\n" +
            "                                </p>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>";
        return code;
    }
}

/**
 * 跳到用户详情页面
 * @param productid
 * @param flag
 */
function getProductDetailForBuyerJump(productid,flag){
    var request_param_buyer ={
        productId:productid,
        queryNoBuy:!flag
    }
    $.cookie('request_param_buyer', JSON.stringify(request_param_buyer), { expires: 7});
    window.location.href='detail_login_buyer.html';
}

/**
 * 卖家显示产品列表
 * @param numPerPage
 * @param curPage
 * @param queryNoBuy false显示全部，true显示未购买
 */
function loadProductListloginForSeller(numPerPage,curPage) {
    $.ajax({
        url: load_productList_login_seller_url,
        type: "get",
        data: {
            prodStateCode: '1',
            numPerPage: numPerPage,
            page: curPage
        },
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        success: function (result, response) {
            if (result.success == true) {
                var num = result.data.length;
                var row_num = Math.ceil(num / 4);//多少行数
                var column_num = num % 4;//最后一列数量
                if (column_num == 0) row_num += 1;//修正满格情况
                var html_code = "";
                for (var i = 1; i <= row_num; i++) {
                    html_code = html_code + "<ul class=\"m-item-list\">";
                    if (i == row_num) {
                        for (var j = 0; j < column_num; j++) {
                            html_code = html_code + generateli(result.data[(i - 1) * 4 + j]);
                        }
                    } else {
                        for (var j = 0; j < 4; j++) {
                            html_code = html_code + generateli(result.data[(i - 1) * 4 + j]);
                        }
                    }
                    html_code = html_code + "</ul>";
                }
                $('#bdclearfix_login_seller').html(html_code);
                $('.M-box11').pagination({
                    pageCount: result.pages,
                    current: curPage,
                    jump: true,
                    callback: function (api) {
                        loadProductListloginForSeller(numPerPage, api.getCurrent());
                    }
                });
            } else if (result.success == false) {
                alert("获取失败" + result.message);
            }
        },
        error: function () {
        }
    });
    function generateli(data) {
        var code = "";

        code = " <li class=\"il-item\">\n" +
            "                        <div class=\"product\">\n" +
            "                            <div class=\"product-hd\">\n";
        if(data.num==null){
            code+="<a>\n" +
                "                                    <img src=\""+data.url+"\" onclick=\"getProductDetailForSeller('"+data.id+"')\" class=\"product-img\">\n" +
                "                                    <p class=\"new-tag\" onclick=\"deleteProductById("+data.id+")\">删除</p>\n" +
                "                                </a>";
        }else{
            code+="<a onclick=\"getProductDetailForSeller('"+data.id+"')\">\n" +
                "                                    <img src=\""+data.url+"\" alt=\"\" class=\"product-img\">\n" +
                "                                    <p class=\"new-tag \">"+"已售"+data.num+"个"+"</p>\n" +
                "                                </a>";
        }
        code+="</div>\n" +
            "                            <div class=\"product-bd\">\n" +
            "                                <div class=\"pro-name\">\n" +
            "                                    <a href=\"\" class=\"product-name\"> "+data.name+"</a>\n" +
            "                                </div>\n" +
            "                                <p class=\"price\">\n" +
            "                                    <span>¥"+data.price+"</span>\n" +
            "                                </p>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>";
        return code;
    }

}
function getProductDetailForSeller(data) {

    alert("getProductDetailBySeller"+data);
}
function deleteProductById(productId) {
    $.ajax({
        url: delete_product_url,
        type: "post",
        data: {
            productId: productId
        },
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        success: function (result, response) {
            if (result.success == true) {
                window.location.href='index_login_seller.html';
            } else if (result.success == false) {
                alert("删除失败" + result.message);
            }
        },
        error: function () {
        }
    });
}
