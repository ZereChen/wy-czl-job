//package com.czl.product.dao;
//
//import com.czl.entity.car.CarEntity;
//import com.czl.entity.product.ProductEntity;
//import com.czl.enumeration.product.ProdStateEnum;
//import com.czl.req.car.CarInsertReq;
//import com.czl.req.car.CarQueryReq;
//import com.czl.req.car.CarUpdateReq;
//import com.czl.req.product.ProdQueryReq;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CarDAOTest {
//    @Autowired
//    private CarDAO carDAO;
//
//    @Test
//    public void testFindCar() throws Exception {
//        CarQueryReq carQueryReq = new CarQueryReq();
//        carQueryReq.setUserId("USERee31ea93e5ac4f7ea0d8b044dff641ec");
//        carQueryReq.setProdStateCode(ProdStateEnum.OPEN.getCode());
//        List<CarEntity> result = carDAO.findCars(carQueryReq);
//        Assert.assertEquals(2,result.size());
//    }
//
//    @Test
//    public void testFindCar1() throws Exception {
//        CarQueryReq carQueryReq = new CarQueryReq();
//        List<CarEntity> result = carDAO.findCars(carQueryReq);
//        Assert.assertEquals(3,result.size());
//    }
//
//    @Test
//    public void testFindCar2() throws Exception {
//        CarQueryReq carQueryReq = new CarQueryReq();
//        carQueryReq.setId("3");
//        List<CarEntity> result = carDAO.findCars(carQueryReq);
//        Assert.assertEquals(1,result.size());
//    }
//
//    @Test
//    public void testDeleteCar() throws Exception {
//        int result = carDAO.deleteCar("4");
//        Assert.assertEquals(0,result);
//    }
//    @Test
//    public void testDeleteCar1() throws Exception {
//        int result = carDAO.deleteCar("44");
//        Assert.assertEquals(1,result);
//    }
//    @Test
//    public void testUpdateCar() throws Exception {
//        CarUpdateReq carUpdateReq = new CarUpdateReq();
//        carUpdateReq.setId("1");
//        carUpdateReq.setNum(5);
//        int result = carDAO.updateCar(carUpdateReq);
//        Assert.assertEquals(1,result);
//    }
//
//    @Test
//    public void testCreateCar1() throws Exception {
//        CarInsertReq carInsertReq = new CarInsertReq();
//        carInsertReq.setId("9876");
//        carInsertReq.setUserId("USERee31ea93e5ac4f7ea0d8b044dff641ec");
//        carInsertReq.setProductId("8");
//        carInsertReq.setNum(6);
//        int result = carDAO.createCar(carInsertReq);
//        Assert.assertEquals(1,result);
//    }
//}