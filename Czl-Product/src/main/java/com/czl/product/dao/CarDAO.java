package com.czl.product.dao;

import com.czl.entity.car.CarEntity;
import com.czl.entity.product.ProductEntity;
import com.czl.req.car.CarInsertReq;
import com.czl.req.car.CarQueryReq;
import com.czl.req.car.CarUpdateReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CarDAO {
    /**
     * 新增购物车产品
     * @param carInsertReq
     * @return
     */
    int createCar(CarInsertReq carInsertReq);

    /**
     * 多条件查询购物车产品
     * @param carQueryReq
     * @return
     */
    List<CarEntity> findCars(CarQueryReq carQueryReq);

    /**
     * 批量删除购物车产品
     * @param carId
     * @return
     */
    int deleteCar(List<String> carId);
    /**
     * 增量更新购物车产品
     * @param carUpdateReq
     */
    int updateCar(CarUpdateReq carUpdateReq);
}
