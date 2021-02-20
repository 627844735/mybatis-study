package org.example.service;/**
 * Created by Enzo Cotter on 2021/2/20.
 */

import com.google.common.base.Preconditions;
import org.example.DaoUtils;
import org.example.dao.OrderItemMapper;
import org.example.domain.OrderItem;

import java.util.List;
import java.util.Objects;

/**
 * @author:zqy
 * @date:2021/2/20 11:25
 * @desc:
 */
public class OrderItemService {

    public Long save(OrderItem orderItem, Long orderId){

        Preconditions.checkArgument(!Objects.isNull(orderItem) && orderId > 0, "args not null");
        return DaoUtils.execute(sqlSession -> {
            OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
            return mapper.save(orderItem,orderId);
        });
    }

    public OrderItem find(Long id){
        Preconditions.checkArgument(id > 0 ,"id error");
        return DaoUtils.execute(sqlSession -> {
            OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
            return mapper.find(id);
        });
    }

    public List<OrderItem> findAllItemByOrderId(Long id){
        Preconditions.checkArgument(id > 0 ,"id error");
        return DaoUtils.execute(sqlSession -> {
            OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
            return mapper.findByOrderId(id);
        });
    }
}
