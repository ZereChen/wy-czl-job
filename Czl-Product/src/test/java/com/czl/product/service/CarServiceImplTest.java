//package com.czl.product.service;
//
//import com.czl.entity.car.CarEntity;
//import com.czl.enumeration.product.ProdStateEnum;
//import com.czl.facade.car.CarService;
//import com.czl.req.car.CarInsertReq;
//import com.czl.req.car.CarQueryReq;
//import com.czl.req.car.CarUpdateReq;
//import com.czl.rsp.Result;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//
///**
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CarServiceImplTest {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private CarService carService;
//
//    @Test
//    public void testFindCar() throws Exception {
//        CarQueryReq carQueryReq = new CarQueryReq();
//        carQueryReq.setUserId("USERee31ea93e5ac4f7ea0d8b044dff641ec");
//        carQueryReq.setProdStateCode(ProdStateEnum.OPEN.getCode());
//        Result<List<CarEntity>> result = carService.findCars(carQueryReq);
//        Assert.assertEquals(2,result.getData().size());
//    }
//
//    @Test
//    public void testFindCar1() throws Exception {
//        CarQueryReq carQueryReq = new CarQueryReq();
//        Result<List<CarEntity>> result = carService.findCars(carQueryReq);
//        Assert.assertEquals(3,result.getData().size());
//    }
//
//    @Test
//    public void testFindCar2() throws Exception {
//        CarQueryReq carQueryReq = new CarQueryReq();
//        carQueryReq.setId("3");
//        Result<List<CarEntity>> result = carService.findCars(carQueryReq);
//        Assert.assertEquals(1,result.getData().size());
//    }
//
//    @Test
//    public void testUpdateCar() throws Exception {
//        CarUpdateReq carUpdateReq = new CarUpdateReq();
//        carUpdateReq.setId("1");
//        carUpdateReq.setNum(5);
//        Result result = carService.updateCar(carUpdateReq);
//        Assert.assertEquals(true,result.isSuccess());
//    }
//    @Test
//    public void testUpdateCar1() throws Exception {
//        CarUpdateReq carUpdateReq = new CarUpdateReq();
//        carUpdateReq.setId("5555da");
//        carUpdateReq.setNum(5);
//        Result result = carService.updateCar(carUpdateReq);
//        Assert.assertEquals(true,result.isSuccess());
//    }
//
//
//    @Test
//    public void testCreateCar() throws Exception {
//        CarInsertReq carInsertReq = new CarInsertReq();
//        carInsertReq.setProductId("1");
//        carInsertReq.setUserId("USERee31ea93e5ac4f7ea0d8b044dff641ec");
//        carInsertReq.setNum(5);
//        Result result = carService.createCar(carInsertReq);
//        Assert.assertEquals(true,result.isSuccess());
//    }
//    @Test
//    public void testCreateCar1() throws Exception {
//        CarInsertReq carInsertReq = new CarInsertReq();
//        carInsertReq.setProductId("67");
//        carInsertReq.setUserId("564");
//        carInsertReq.setNum(5);
//        Result result = carService.createCar(carInsertReq);
//        Assert.assertEquals(true,result.isSuccess());
//    }
//    @Test
//    public void testCreateCar2() throws Exception {
//        CarInsertReq carInsertReq = new CarInsertReq();
//        carInsertReq.setProductId("67");
//        carInsertReq.setUserId("565");
//        carInsertReq.setNum(0);
//        Result result = carService.createCar(carInsertReq);
//        System.out.println(result.getMessage());
//        Assert.assertEquals(false,result.isSuccess());
//    }
//
//    @Test
//    public void testDeleteCar() throws Exception {
//        Result result = carService.deleteCar(null);
//        System.out.println(result.getMessage());
//        Assert.assertEquals(false,result.isSuccess());
//    }
//}