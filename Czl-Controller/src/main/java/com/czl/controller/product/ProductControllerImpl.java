package com.czl.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czl.entity.product.BrandEntity;
import com.czl.entity.product.CategoryEntity;
import com.czl.entity.product.ProdImageEntity;
import com.czl.entity.product.ProductEntity;
import com.czl.facade.product.ProductService;
import com.czl.req.product.*;
import com.czl.rsp.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zerechen
 * @date 2017/10/27 下午10:28
 */
@RestController
public class ProductControllerImpl implements ProductController {

    @Reference(version = "1.0.0")
    private ProductService productService;


    @Override
    public Result createProduct(ProdInsertReq prodInsertReq) {
        //新增产品
        return productService.createProduct(prodInsertReq);
    }

    @Override
    public Result<ProdImageEntity> uploadImage(MultipartFile file) {
        return null;
    }

    @Override
    public Result updateProduct(ProdUpdateReq prodUpdateReq) {
        //增量更新产品
        return productService.updateProduct(prodUpdateReq);
    }

    @Override
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq) {
        return productService.findProducts(prodQueryReq);
    }

    @Override
    public Result createCategoty(CategoryEntity categoryEntity) {
        return productService.createCategoty(categoryEntity);
    }

    @Override
    public Result modifyCategory(CategoryEntity categoryEntity) {
        return productService.modifyCategory(categoryEntity);
    }

    @Override
    public Result deleteCategory(String categoryId) {
        return productService.deleteCategory(categoryId);
    }

    @Override
    public Result<List<CategoryEntity>> findCategorys(CategoryQueryReq categoryQueryReq) {
        return productService.findCategorys(categoryQueryReq);
    }

    @Override
    public Result createBrand(BrandInsertReq brandInsertReq) {
        return productService.createBrand(brandInsertReq);
    }

    @Override
    public Result modifyBrand(BrandInsertReq brandInsertReq) {
        return productService.modifyBrand(brandInsertReq);
    }

    @Override
    public Result<List<BrandEntity>> findBrands(BrandQueryReq brandQueryReq) {
        return productService.findBrands(brandQueryReq);
    }
}
