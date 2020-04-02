package com.czl.exception;

import java.io.Serializable;

import static com.czl.exception.ExpPrefix.*;

/**
 * @Author zerechen
 * 全局的异常状态码 和 异常描述
 *
 * PS:异常码一共由5位组成，前两位为固定前缀，请参考{@link ExpPrefix}
 */
public enum ExpCodeEnum implements Serializable {

    /** 通用异常 */
    UNKNOW_ERROR(ComExpPrefix + "000", "未知异常"),
    ERROR_404(ComExpPrefix + "001", "没有该接口"),
    PARAM_NULL(ComExpPrefix + "002", "参数为空"),
    NO_REPEAT(ComExpPrefix + "003", "请勿重复提交"),
    SESSION_NULL(ComExpPrefix + "004", "请求头中SessionId不存在"),
    HTTP_REQ_METHOD_ERROR(ComExpPrefix + "005", "HTTP请求方法不正确"),
    JSONERROR(ComExpPrefix + "006", "JSON解析异常"),
    PERMISSION_TOKEN_EXPIRED(ComExpPrefix + "007", "Token过期"),
    PERMISSION_TOKEN_INVALID(ComExpPrefix + "008", "token解析异常"),
    PERMISSION_SIGNATURE_ERROR(ComExpPrefix + "009", "签名失败"),

    /** User模块异常 */
    USERNAME_NULL(UserExpPrefix + "000", "用户名为空"),
    PASSWD_NULL(UserExpPrefix + "001", "密码为空"),
    LOGIN_FAIL(UserExpPrefix + "003", "登录失败"),
    UNLOGIN(UserExpPrefix + "004", "尚未登录"),
    NO_PERMISSION(UserExpPrefix + "005", "没有权限"),
    USER_ID_NULL(UserExpPrefix + "006", "用户ID为空"),
    MAIL_NULL(UserExpPrefix + "007", "电子邮件为空"),
    USERTYPE_NULL(UserExpPrefix + "008", "用户类别为空"),
    LICENCE_NULL(UserExpPrefix + "009", "营业执照为空"),
    COMPANYNAME_NULL(UserExpPrefix + "010", "企业名称为空"),
    ROLE_NULL(UserExpPrefix + "011", "角色为空"),
    ROLEID_NULL(UserExpPrefix + "012", "roleId为空"),
    MENUIDLIST_NULL(UserExpPrefix + "013", "menuIdList为空"),
    PERMISSIONIDLIST_NULL(UserExpPrefix + "014", "permissionIdList为空"),
    NAME_NULL(UserExpPrefix + "015", "name为空"),
    LOCATIONID_NULL(UserExpPrefix + "016", "locationId为空"),
    LOCATIONUPDATEREQ_NULL(UserExpPrefix + "017", "LocationUpdateReq为空"),

    /** Product模块异常 */
    PRODUCT_NAME_NULL(ProdExpPrefix + "000", "产品名称为空"),
    PRODUCT_MARKETPRICE_NULL(ProdExpPrefix + "001", "产品市场价为空"),
    PRODUCT_SHOPPRICE_NULL(ProdExpPrefix + "002", "产品本店价为空"),
    PRODUCT_STOCK_ZERO(ProdExpPrefix + "003", "产品库存为0"),
    PRODUCT_WEIGHT_NULL(ProdExpPrefix + "004", "产品重量为空"),
    PRODUCT_ID_NULL(ProdExpPrefix + "005", "产品ID为空"),
    PRODUCT_SUBCATEGENTITY_NULL(ProdExpPrefix + "006", "产品二级分类为空"),
    PRODUCT_BRANDENTITY_NULL(ProdExpPrefix + "007", "产品品牌为空"),
    PRODUCT_COMPANYENTITY_NULL(ProdExpPrefix + "008", "产品所属企业为空"),
    PRODUCT_CREATE_FAIL(ProdExpPrefix + "009", "产品新增失败"),
    PRODUCT_UPDATE_FAIL(ProdExpPrefix + "010", "产品更新失败"),
    PRODUCT_SELECT_FAIL(ProdExpPrefix + "011", "没有符合条件的查询结果，产品查询失败"),
    PRODUCT_DELETE_FAIL(ProdExpPrefix + "011", "产品删除失败"),
    PRODUCT_NO_EXISTENT(ProdExpPrefix + "025", "产品不存在，或者已下架"),
    /** Car异常 前缀统一使用 ProdExpPrefix */
    CAR_MERGE_FAIL(ProdExpPrefix + "013","购物车产品合并失败"),
    CAR_CREATE_FAIL(ProdExpPrefix + "014","购物车产品新增失败"),
    CAR_DELETE_FAIL(ProdExpPrefix + "015","购物车产品删除失败"),
    CAR_UPDATE_FAIL(ProdExpPrefix + "016","购物车产品更新失败"),
    CAR_SELECT_FAIL(ProdExpPrefix + "017","没有符合条件的查询结果，购物车产品查询失败"),
    CAR_NUM_FAIL(ProdExpPrefix + "018","购物车产品数量错误"),
    CAR_ID_NULL(ProdExpPrefix + "019","购物车产品ID为空"),

    /** BUY异常 前缀统一使用 ProdExpPrefix */
    BUY_SELECT_FAIL(ProdExpPrefix + "017","没有符合条件的查询结果，购买记录产品查询失败");




    private String code;
    private String message;

    private ExpCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    ExpCodeEnum(){}

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
