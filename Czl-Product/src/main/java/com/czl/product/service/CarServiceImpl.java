package com.czl.product.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.czl.entity.car.CarEntity;
import com.czl.enumeration.KeyGeneratorPrefixEnum;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.car.CarService;
import com.czl.product.dao.CarDAO;
import com.czl.req.car.CarInsertReq;
import com.czl.req.car.CarQueryReq;
import com.czl.req.car.CarUpdateReq;
import com.czl.req.product.ProdInsertReq;
import com.czl.rsp.Result;
import com.czl.utils.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zerechen
 * @description
 */
@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;


    @Override
    public Result createCar(CarInsertReq carInsertReq) {
        Result checkResult = null;
        if((checkResult=checkCarInsertReqParam(carInsertReq)) != null)   return checkResult;

        //先查是否有对应的记录，有就更新
        CarQueryReq carQueryReq = buildCarQueryReq(carInsertReq);
        List<CarEntity> carEntityList = carDAO.findCars(carQueryReq);
        if(CollectionUtils.isEmpty(carEntityList)){
            //新增
            carInsertReq.setId(KeyGenerator.getKey(KeyGeneratorPrefixEnum.CAR_ID_PREFIX));
            int result = carDAO.createCar(carInsertReq);
            if (result == 0){
                return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_CREATE_FAIL));
            }
        }else{
            //更新
            int result = carDAO.updateCar(buildCarUpdateReq(carInsertReq,carEntityList.get(0)));
            if (result == 0){
                return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_MERGE_FAIL));
            }
        }
        return Result.newSuccessResult();
    }
    private CarUpdateReq buildCarUpdateReq(CarInsertReq carInsertReq,CarEntity carEntity){
        CarUpdateReq carUpdateReq = new CarUpdateReq();
        carUpdateReq.setId(carEntity.getId());
        carUpdateReq.setNum(carEntity.getNum()+carInsertReq.getNum());
        return carUpdateReq;
    }

    private CarQueryReq buildCarQueryReq(CarInsertReq carInsertReq){
        CarQueryReq carQueryReq = new CarQueryReq();
        carQueryReq.setUserId(carInsertReq.getUserId());
        carQueryReq.setProductId(carInsertReq.getProductId());
        return carQueryReq;
    }
    private Result checkCarInsertReqParam(CarInsertReq carInsertReq) {
        if (StringUtils.isEmpty(carInsertReq.getProductId())) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.PRODUCT_ID_NULL));
        }
        if (StringUtils.isEmpty(carInsertReq.getUserId())) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.USER_ID_NULL));
        }
        if (carInsertReq.getNum() == null || carInsertReq.getNum()<=0) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_NUM_FAIL));
        }
        return null;
    }
    @Override
    public Result updateCar(CarUpdateReq carUpdateReq) {
        Result checkResult = null;
        if((checkResult=checkCarUpdateReqParam(carUpdateReq)) != null)   return checkResult;

        int result = carDAO.updateCar(carUpdateReq);
        if (result == 0){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_UPDATE_FAIL));
        }
        return Result.newSuccessResult();
    }

    private Result checkCarUpdateReqParam(CarUpdateReq carUpdateReq) {
        if (StringUtils.isEmpty(carUpdateReq.getId())) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_ID_NULL));
        }
        if (carUpdateReq.getNum() == null || carUpdateReq.getNum()<=0) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_NUM_FAIL));
        }
        return null;
    }

    @Override
    public Result<List<CarEntity>> findCars(CarQueryReq carQueryReq) {
        List<CarEntity> carEntityList= carDAO.findCars(carQueryReq);

        if (CollectionUtils.isEmpty(carEntityList)){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_SELECT_FAIL));
        }
        return Result.newSuccessResult(carEntityList);
    }

    @Override
    public Result deleteCar(List<String> carId) {
        if (CollectionUtils.isEmpty(carId)) {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_ID_NULL));
        }
        int result = carDAO.deleteCar(carId);
        if (result == 0){
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.CAR_DELETE_FAIL));
        }
        return Result.newSuccessResult();
    }
}
