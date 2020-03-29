package com.czl.controller.product;

import com.czl.annotation.Login;
import com.czl.annotation.Permission;
import com.czl.entity.product.ProdImageEntity;
import com.czl.req.product.*;
import com.czl.rsp.Result;
import com.czl.entity.product.ProductEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zerechen
 * @description 产品系统的Controller
 */
@RestController
@CrossOrigin
public interface ProductController {


    /**
     * 创建产品
     * @param prodInsertReq 产品详情
     * @return 是否创建成功
     */
    @PostMapping("product")
    @Login
    @Permission("product:create")
    public Result createProduct(ProdInsertReq prodInsertReq);

    /**
     * 上传图片
     * PS：需要定时清除没被引用的图片
     * @param file 待上传的文件
     * @return 图片详情
     */
    @PostMapping("image")
    @Login
    @Permission("image:upload")
    public Result<ProdImageEntity> uploadImage(MultipartFile file);

    /**
     * 修改产品
     * @param prodUpdateReq 待修改产品（id必填 & 只提交待修改字段即可）
     * @return 是否修改成功
     */
    @PutMapping("product")
    @Login
    @Permission("product:update")
    public Result updateProduct(ProdUpdateReq prodUpdateReq);

    /**
     * 查询产品
     * @param prodQueryReq 产品查询请求
     * @return 产品查询结果
     */
    @GetMapping("product/query")
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq);


    /**
     * 删除产品
     * @param productId 待删除产品的id
     * @return 删除结果
     */
    @DeleteMapping("product/delete")
    @Login
    @Permission("product:delete")
    public Result deleteProduct(String productId);

    /**
     * 卖家查询产品
     * @param prodQueryReq 产品查询请求
     * @return 产品查询结果
     */
    @GetMapping("product/sellerQuery")
    @Login
    @Permission("product:sellerQuery")
    public Result<List<ProductEntity>> findOrdersForSeller(ProdQueryReq prodQueryReq);

    /**
     * 买家查询产品
     * @param prodQueryReq 产品查询请求
     * @return 产品查询结果
     */
    @GetMapping("product/buyerQuery")
    @Login
    @Permission("product:buyerQuery")
    public Result<List<ProductEntity>> findOrdersForBuyer(ProdQueryReq prodQueryReq);
}
