package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 地址管理(AddressBook)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-06 09:50:39
 */
@Repository
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
