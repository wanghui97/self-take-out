package com.hwang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwang.entity.AddressBook;
import com.hwang.utiltools.ResponseResult;


/**
 * 地址管理(AddressBook)表服务接口
 *
 * @author hwang
 * @since 2023-05-06 09:50:39
 */
public interface AddressBookService extends IService<AddressBook> {

    ResponseResult getCurrentUserAddressBookList();

    ResponseResult updateDefaultAddressBook(Long addressBookId);

    ResponseResult getDefaultAddressBook();
}
