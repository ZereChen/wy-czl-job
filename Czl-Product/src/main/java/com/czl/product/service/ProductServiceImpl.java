package com.czl.product.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.czl.entity.product.ProductEntity;
import com.czl.enumeration.KeyGeneratorPrefixEnum;
import com.czl.enumeration.product.ProdStateEnum;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.product.ProductService;
import com.czl.product.dao.ProductDAO;
import com.czl.req.product.*;
import com.czl.rsp.Result;
import com.czl.utils.KeyGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zerechen
 * @description
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;


    @Override
    public Result createProduct(ProdInsertReq prodInsertReq){
        //参数校验
        checkProductParam(prodInsertReq);
        //组装新产品
        ProdInsertReq product = makeProdInsertReq(prodInsertReq);
        //新增产品
        int result = productDAO.createProduct(product);
        if (result == 0){
            //新增失败
            throw new CommonBizException(ExpCodeEnum.PRODUCT_CREATE_FAIL);
        }
        //新增成功
        return Result.newSuccessResult();
    }

    @Override
    public Result updateProduct(ProdUpdateReq prodUpdateReq) {
        //增量更新产品
        int result = productDAO.updateProduct(prodUpdateReq);
        if (result == 0){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_UPDATE_FAIL);
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq) {
        PageHelper.startPage(prodQueryReq.getPage(), prodQueryReq.getNumPerPage());
        List<ProductEntity> productEntityList= productDAO.findProducts(prodQueryReq);

        PageInfo info=new PageInfo(productEntityList);
        productEntityList=info.getList();
        if (CollectionUtils.isEmpty(productEntityList)){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.PRODUCT_SELECT_FAIL));
        }
        return Result.newSuccessResult(productEntityList,info.getPages());
    }




    /**
     * 校验新增产品时参数
     * 关键字段不能为空
     * @param prodInsertReq
     */
    private void checkProductParam(ProdInsertReq prodInsertReq){
        if (StringUtils.isEmpty(prodInsertReq.getProdName())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_NAME_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getMarketPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_MARKETPRICE_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getShopPrice())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_SHOPPRICE_NULL);
        }else if (prodInsertReq.getStock()==0){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_STOCK_ZERO);
        }else if (StringUtils.isEmpty(prodInsertReq.getWeight())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_WEIGHT_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getTopCateEntityID())){
//            throw new CommonBizException(ExpCodeEnum.PRODUCT_TOPCATEENTITY_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getSubCategEntityID())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_SUBCATEGENTITY_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getBrandEntityID())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_BRANDENTITY_NULL);
        }else if (StringUtils.isEmpty(prodInsertReq.getCompanyEntityID())){
            throw new CommonBizException(ExpCodeEnum.PRODUCT_COMPANYENTITY_NULL);
        }
    }

    /**
     * 组装新增产品对象
     * @param prodInsertReq
     * @return
     */
    private ProdInsertReq makeProdInsertReq(ProdInsertReq prodInsertReq){
        ProdInsertReq newProduct = new ProdInsertReq();
        //使用BeanUtils复制属性，注意顺序！
        BeanUtils.copyProperties(prodInsertReq,newProduct);
        newProduct.setId(KeyGenerator.getKey(KeyGeneratorPrefixEnum.PRODUCT_ID_PREFIX));
        //新增产品默认销量为0
        newProduct.setSales(0);
        //新增产品默认状态是上架
        newProduct.setProdState(ProdStateEnum.OPEN.getCode());
        return newProduct;
    }

    @Override
    public Result deleteProduct(String ProductId) {
        int result = productDAO.deleteProduct(ProductId);
        if (result == 0){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.PRODUCT_DELETE_FAIL));
        }
        return Result.newSuccessResult();
    }

    @Override
    public Result<List<ProductEntity>> findProductsForBuyer(ProdQueryReqForLogin prodQueryReqForLogin) {
        prodQueryReqForLogin.setProdStateCode(ProdStateEnum.OPEN.getCode());

        if (StringUtils.isEmpty(prodQueryReqForLogin.getUserId())) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.USER_ID_NULL));
        }

        PageHelper.startPage(prodQueryReqForLogin.getPage(), prodQueryReqForLogin.getNumPerPage());
        List<ProductEntity> productEntityList = null;
        if(prodQueryReqForLogin.isQueryNoBuy()){
            //只查询未购买的
            productEntityList = productDAO.findProductsForBuyerNoBuyed(prodQueryReqForLogin);
        }else{
            //查询所有已购买的
            productEntityList = productDAO.findProductsForBuyer(prodQueryReqForLogin);
        }
        PageInfo info=new PageInfo(productEntityList);
        productEntityList=info.getList();
        if (CollectionUtils.isEmpty(productEntityList)){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.PRODUCT_SELECT_FAIL));
        }
        return Result.newSuccessResult(productEntityList,info.getPages());
    }

    @Override
    public Result<List<ProductEntity>> findProductsForSeller(ProdQueryReqForLogin prodQueryReqForLogin) {
        prodQueryReqForLogin.setProdStateCode(ProdStateEnum.OPEN.getCode());
        PageHelper.startPage(prodQueryReqForLogin.getPage(), prodQueryReqForLogin.getNumPerPage());
        List<ProductEntity>  productEntityList = productDAO.findProductsForSeller(prodQueryReqForLogin);
        PageInfo info=new PageInfo(productEntityList);
        productEntityList=info.getList();
        if (CollectionUtils.isEmpty(productEntityList)){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.PRODUCT_SELECT_FAIL));
        }
        return Result.newSuccessResult(productEntityList,info.getPages());
    }
}
