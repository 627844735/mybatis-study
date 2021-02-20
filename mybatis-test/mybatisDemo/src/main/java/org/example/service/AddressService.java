package org.example.service;/**
 * Created by Enzo Cotter on 2021/2/20.
 */

import com.google.common.base.Preconditions;
import org.example.DaoUtils;
import org.example.dao.AddressMapper;
import org.example.domain.Address;

import java.util.List;
import java.util.Objects;

/**
 * @author:zqy
 * @date:2021/2/20 10:27
 * @desc:
 */
public class AddressService {

    //根据消费者查询所有的地址信息
    public List<Address> findAllAddressByCustomerId(long id){
        Preconditions.checkArgument(id > 0,"id error");
        return DaoUtils.execute(sqlSession -> {
            AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
            return addressMapper.findAll(id);
        });
    }

    //根据ID查看地址信息
    public Address findById(long id){
        Preconditions.checkArgument(id > 0,"address id error");
        return DaoUtils.execute(sqlSession -> {
            AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
            return addressMapper.find(id);
        });
    }

    //根据消费者ID添加地址
    public int save(Address address,Long customerId){
        Preconditions.checkArgument(!Objects.isNull(address.getCity()) &&
                                                 !Objects.isNull(address.getStreet()) &&
                                                 !Objects.isNull(address.getCountry()) &&
                                                  customerId > 0,"args not null");

        return DaoUtils.execute(sqlSession -> {
            AddressMapper mapperAddress = sqlSession.getMapper(AddressMapper.class);
            return mapperAddress.save(address, customerId);
        });
    }
}
