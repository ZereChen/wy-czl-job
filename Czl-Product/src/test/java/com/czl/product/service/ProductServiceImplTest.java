//package com.czl.product.service;
//
//import com.czl.entity.product.ProductEntity;
//import com.czl.facade.product.ProductService;
//import com.czl.req.product.*;
//import com.czl.rsp.Result;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ProductServiceImplTest {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private ProductService productService;
//
//
////    @Test
////    public void testCreateProduct() throws Exception {
////        ProdInsertReq prodInsertReq = new ProdInsertReq();
////        prodInsertReq.setProdName("香辣蟹");
////        prodInsertReq.setMarketPrice("99.00");
////        prodInsertReq.setShopPrice("88.00");
////        prodInsertReq.setWeight("100");
////        prodInsertReq.setStock(100);
////        prodInsertReq.setTopCateEntityID("12345");
////        prodInsertReq.setSubCategEntityID("23456");
////        //prodInsertReq.setBrandEntityID("lihang");
////        //prodInsertReq.setContent("太阳能光伏板");
////        prodInsertReq.setCompanyEntityID("xzchain");
////
////
////        productService.createProduct(prodInsertReq);
////    }
////    @Test
////    public void testUpdateProduct() throws Exception {
////        ProdUpdateReq prodUpdateReq = new ProdUpdateReq();
////        prodUpdateReq.setId("c142339174e74536bcbe3d277e22e031");
////        //prodInsertReq.setProdName("香辣蟹");
////        //prodInsertReq.setMarketPrice("99.00");
////        //prodInsertReq.setShopPrice("88.00");
//////        prodInsertReq.setWeight("100");
//////        prodInsertReq.setStock(100);
//////        prodInsertReq.setTopCateEntityID("12345");
//////        prodInsertReq.setSubCategEntityID("23456");
////        //prodInsertReq.setBrandEntityID("lihang");
////        prodUpdateReq.setContent("不好吃不要钱");
////        //prodInsertReq.setCompanyEntityID("xzchain");
////
////
////        productService.updateProduct(prodUpdateReq);
////    }
//
//    @Test
//    public void testFindProducts_by10(){
//        ProdQueryReq prodQueryReq = new ProdQueryReq();
//        prodQueryReq.setPage(2);//第一页开始
//        prodQueryReq.setNumPerPage(10);
//
//        Result<List<ProductEntity>> productEntityList = productService.findProducts(prodQueryReq);
//        logger.info(productEntityList.toString());
//        Assert.assertEquals(new Integer(2),productEntityList.getPages());
//        Assert.assertEquals(9,productEntityList.getData().size());
//    }
//    @Test
//    public void testFindProducts_byState(){
//        ProdQueryReq prodQueryReq = new ProdQueryReq();
//        prodQueryReq.setPage(2);
//        prodQueryReq.setNumPerPage(10);
//       prodQueryReq.setProdStateCode(1);
//
//        Result<List<ProductEntity>> productEntityList = productService.findProducts(prodQueryReq);
//        logger.info(productEntityList.toString());
//        Assert.assertEquals(new Integer(2),productEntityList.getPages());
//        Assert.assertEquals(8,productEntityList.getData().size());
//    }
//}