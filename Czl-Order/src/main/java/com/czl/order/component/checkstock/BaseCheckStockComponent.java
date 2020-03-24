package com.czl.order.component.checkstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czl.context.OrderProcessContext;
import com.czl.entity.product.ProductEntity;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.product.ProductService;
import com.czl.order.component.BaseComponent;
import com.czl.req.order.OrderInsertReq;

import java.util.Map;

/**
 * @author zerechen
 * @date 2017/11/7 下午1:17
 * @description 库存校验组件
 */
public class BaseCheckStockComponent extends BaseComponent {

    @Reference(version = "1.0.0")
    private ProductService productService;

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);

        // 获取产品Entity的Map
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();
        Map<ProductEntity ,Integer> prodEntityCountMap = orderInsertReq.getProdEntityCountMap();

        // 检查库存
        checkStock(prodEntityCountMap);

        afterHandle(orderProcessContext);
    }

    /**
     * 校验库存是否足够
     * @param prodEntityCountMap 产品-购买数量 集合
     */
    private void checkStock(Map<ProductEntity, Integer> prodEntityCountMap) {
        // 校验库存
        for (ProductEntity productEntity : prodEntityCountMap.keySet()) {
            // 获取购买量
            Integer count = prodEntityCountMap.get(productEntity);
            // 校验
            if (productEntity.getStock() < count) {
                throw new CommonBizException(ExpCodeEnum.STOCK_LOW);
            }
        }
    }

}
