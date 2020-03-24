package com.czl.order.processor;

import com.czl.order.annotation.InjectComponents;
import com.czl.order.component.BaseComponent;
import com.czl.order.component.checkparam.PlaceOrderCheckParamComponent;
import com.czl.order.component.checkstock.CommonCheckStockComponent;
import com.czl.order.component.createorder.CreateOrderComponent;
import com.czl.order.component.datatransfer.ProdCountMapTransferComponent;
import com.czl.order.component.pay.CommonPayComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zerechen
 * @date 2017/11/6 下午1:59
 *
 * @description 下单受理器
 */
@Component
public class PlaceOrderProcessor extends Processor {

    @InjectComponents({
            // 参数校验
            PlaceOrderCheckParamComponent.class,
            // 数据转化(prodIdCountMap——>prodEntityCountMap)
            ProdCountMapTransferComponent.class,
            // 库存检查
            CommonCheckStockComponent.class,
            // 创建订单
            CreateOrderComponent.class,
            // 支付
            CommonPayComponent.class,
    })
    /** 业务组件列表(当前处理器需要处理的组件列表) */
    protected List<BaseComponent> componentList;

    @Override
    protected void overrideSuperComponentList() {
        super.componentList = this.componentList;
    }
}
