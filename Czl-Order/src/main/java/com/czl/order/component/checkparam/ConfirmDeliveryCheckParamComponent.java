package com.czl.order.component.checkparam;

import com.czl.context.OrderProcessContext;
import com.czl.exception.ExpCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author zerechen
 * @date 2017/11/10 下午5:30
 * @description
 */
@Component
public class ConfirmDeliveryCheckParamComponent extends BaseCheckParamComponent {
    @Override
    protected void privateParamCheck(OrderProcessContext orderProcessContext) {
        // 物流单号不能为空
        checkParam(orderProcessContext.getOrderProcessReq().getReqData()+"", ExpCodeEnum.EXPRESS_NO_NULL);
    }
}
