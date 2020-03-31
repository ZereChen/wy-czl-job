package com.czl.controller.car;

import com.alibaba.dubbo.config.annotation.Reference;
import com.czl.controller.product.ProductController;
import com.czl.entity.car.CarEntity;
import com.czl.entity.product.ProdImageEntity;
import com.czl.entity.product.ProductEntity;
import com.czl.facade.car.CarService;
import com.czl.facade.product.ProductService;
import com.czl.req.car.CarInsertReq;
import com.czl.req.car.CarQueryReq;
import com.czl.req.car.CarUpdateReq;
import com.czl.req.product.ProdInsertReq;
import com.czl.req.product.ProdQueryReq;
import com.czl.req.product.ProdUpdateReq;
import com.czl.rsp.Result;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zerechen
 */
@RestController
public class CarControllerImpl implements CarController {

    @Reference(version = "1.0.0" ,timeout = 100000)
    private CarService carService;


    @Override
    public Result createCar(CarInsertReq carInsertReq) {
        return carService.createCar(carInsertReq);
    }

    @Override
    public Result<List<CarEntity>> findCars(CarQueryReq carQueryReq) {
        return carService.findCars(carQueryReq);
    }

    @Override
    public Result deleteCar(String carId) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(carId);
        return carService.deleteCar(arrayList);
    }

    @Override
    public Result updateCar(CarUpdateReq carUpdateReq) {
        return carService.updateCar(carUpdateReq);
    }

}
