package com.czl.facade.car;

import com.czl.entity.car.CarEntity;
import com.czl.req.car.CarInsertReq;
import com.czl.req.car.CarQueryReq;
import com.czl.req.car.CarUpdateReq;
import com.czl.rsp.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zerechen
 * @description
 */
public interface CarService {
    /**
     * 新增购物车产品
     * @param carInsertReq
     * @return
     */
    Result createCar(CarInsertReq carInsertReq);

    /**
     * 增量更新购物车产品
     * @param carUpdateReq
     * @return
     */
    Result updateCar(CarUpdateReq carUpdateReq);

    /**
     * 多条件查询所有购物车（支持分页）
     * @param carQueryReq
     * @return
     */
    Result<List<CarEntity>> findCars(CarQueryReq carQueryReq);

    /**
     * 删除购物车产品
     * @param carId
     * @return
     */
    Result deleteCar(List<String> carId);


}
