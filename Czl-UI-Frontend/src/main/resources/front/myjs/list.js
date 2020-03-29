/**
 *
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
                            console.log("j="+j+" column_num="+column_num);
                            html_code=html_code+generateil(result.data[(i-1)*4+j]);
                        }
                    }else{
                        for(var j=0;j<4;j++){
                            html_code=html_code+generateil(result.data[(i-1)*4+j]);
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
        error : function () {
        }
    });
    function generateil(data) {
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
    }

    function loadProductListloginForBuyer(numPerPage,curPage) {

    }
}