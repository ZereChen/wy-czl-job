package com.czl.controller.car;

import com.czl.annotation.Login;
import com.czl.annotation.Permission;
import com.czl.entity.car.CarEntity;
import com.czl.req.car.CarInsertReq;
import com.czl.req.car.CarQueryReq;
import com.czl.req.car.CarUpdateReq;
import com.czl.rsp.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zerechen
 * @description 购物车系统的Controller
 */
@RestController
@CrossOrigin
public interface CarController {


    /**
     * 创建购物车产品
     * @param carInsertReq 购物车产品详情
     * @return 是否创建成功
     */
    @PostMapping("car/create")
    @Permission("car:create")
    @Login
    public Result createCar(CarInsertReq carInsertReq);

    /**
     * 查询购物车产品
     * @param carQueryReq 购物车产品查询请求
     * @return 购物车产品查询结果
     */
    @GetMapping("car/query")
    @Permission("car:query")
    @Login
    public Result<List<CarEntity>> findCars(CarQueryReq carQueryReq);


    /**
     * 删除购物车产品
     * @param carId 待删除购物车的id
     * @return 删除结果
     */
    @GetMapping("car/delete")
    @Permission("car:delete")
    @Login
    public Result deleteCar(@RequestParam(value = "carId") String carId);

    /**
     * 修改购物车产品数量
     * @param carUpdateReq 待修改购物车信息
     * @return 是否修改成功
     */
    @PutMapping("car")
    @Permission("car:update")
    @Login
    public Result updateCar(CarUpdateReq carUpdateReq);

}
