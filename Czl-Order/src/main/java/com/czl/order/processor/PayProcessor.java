package com.czl.order.processor;

import com.czl.order.annotation.InjectComponents;
import com.czl.order.component.BaseComponent;
import com.czl.order.component.checkparam.NoPrivateCheckParamComponent;
import com.czl.order.component.checkstock.CommonCheckStockComponent;
import com.czl.order.component.datatransfer.ProdCountMapTransferComponent;
import com.czl.order.component.datatransfer.ProdIdCountMapTransferComponent;
import com.czl.order.component.idempotent.PayIdempotentComponent;
import com.czl.order.component.pay.CommonPayComponent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zerechen
 * @date 2017/11/10 下午4:14
 *
 * @description 支付受理器
 */
@Component
public class PayProcessor extends Processor {

    @InjectComponents({
            // 参数校验
            NoPrivateCheckParamComponent.class,
            // 幂等检查
            PayIdempotentComponent.class,
            // 根据orderId还原prodIdCountMap
            ProdIdCountMapTransferComponent.class,
            // prodIdCountMap——>prodEntityCountMap
            ProdCountMapTransferComponent.class,
            // 库存检查
            CommonCheckStockComponent.class,
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
