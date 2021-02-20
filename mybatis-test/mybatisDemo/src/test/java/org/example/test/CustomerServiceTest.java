package org.example.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.ibatis.reflection.Reflector;
import org.example.domain.Address;
import org.example.domain.Customer;
import org.example.domain.Order;
import org.example.domain.OrderItem;
import org.example.domain.Product;
import org.example.service.AddressService;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * Created on 2020-10-30
 */
public class CustomerServiceTest {

    private static CustomerService customerService;

    private static OrderService orderService;

    private static ProductService productService;

    private static AddressService addressService;

    @Before
    public void init() {
        customerService = new CustomerService();
        orderService = new OrderService();
        productService = new ProductService();
        addressService = new AddressService();
    }

    @Test
    public void test01() {
        // 创建一个用户
        long customerId = customerService.register("sizhengYang", "12345654321");
        // 为用户添加一个配送地址
        long addressId = customerService.addAddress(customerId,
                "NiuLanShan", "NiuLanShi", "AiRenGuo");
        System.out.println(addressId);
        // 查询用户信息以及地址信息
        Customer customer = customerService.find(customerId);
        System.out.println(customer);
        Customer customer2 = customerService.findWithAddress(customerId);
        System.out.println(customer2);
        List<Address> addressList = customerService.findAllAddress(customerId);
        addressList.stream().forEach(System.out::println);

        // 入库一些商品
        Product product = new Product();
        product.setName("MyBatis");
        product.setDescription("MyBatis-ViewStudy");
        product.setPrice(new BigDecimal(99));
        long productId = productService.createProduct(product);
        System.out.println("create productId:" + productId);

        // 创建一个订单
        Order order = new Order();
        order.setCustomer(customer); // 买家
        order.setDeliveryAddress(addressList.get(0)); // 配送地址
        // 生成购买条目
        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(20);
        orderItem.setProduct(product);
        order.setOrderItems(Lists.newArrayList(orderItem));
        long orderId = orderService.createOrder(order);
        System.out.println("create orderId:" + orderId);

        Order order2 = orderService.find(orderId);
        System.out.println(order2);
    }

    //测试地址接口
    @Test
    public void addressTest(){
        List<Address> allAddress = addressService.findAllAddressByCustomerId(3);
        System.out.println(allAddress);

        Address newAddress = new Address();
        newAddress.setCity("ChengDu");
        newAddress.setStreet("HongGuang");
        newAddress.setCountry("China");
        addressService.save(newAddress, 3L);
        System.out.println(newAddress.getId());

        Address address = addressService.findById(newAddress.getId());
        System.out.println(address);
    }

    //测试订单商品接口.
    @Test
    public void OrderItemTest(){
        Reflector reflector = new Reflector(Address.class);
        System.out.println(reflector);
    }
}