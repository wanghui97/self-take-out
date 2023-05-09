package com.hwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.AddressBook;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.mapper.AddressBookMapper;
import com.hwang.service.AddressBookService;
import com.hwang.utiltools.BaseContextUtil;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址管理(AddressBook)表服务实现类
 *
 * @author hwang
 * @since 2023-05-06 09:50:39
 */
@Service("addressBookService")
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
    @Override
    public ResponseResult getCurrentUserAddressBookList() {
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getUserId, BaseContextUtil.getCurrentId());
        List<AddressBook> addressBookList = this.list(addressBookLambdaQueryWrapper);
        return ResponseResult.okResult(addressBookList);
    }

    @Override
    public ResponseResult updateDefaultAddressBook(Long addressBookId) {
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getIsDefault, CodeLibraryUtil.IS_DEFAULT);
        AddressBook addressBookOldDefault = this.getOne(addressBookLambdaQueryWrapper);
        addressBookOldDefault.setIsDefault(CodeLibraryUtil.NO_DEFAULT);
        AddressBook addressBook = this.getById(addressBookId);
        addressBook.setIsDefault(CodeLibraryUtil.IS_DEFAULT);
        List<AddressBook> addressBookList = new ArrayList<>();
        addressBookList.add(addressBook);
        addressBookList.add(addressBookOldDefault);
        boolean updateFlag = this.updateBatchById(addressBookList);
        if(updateFlag){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public ResponseResult getDefaultAddressBook() {
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getIsDefault, CodeLibraryUtil.IS_DEFAULT);
        AddressBook addressBookOldDefault = this.getOne(addressBookLambdaQueryWrapper);
        return ResponseResult.okResult(addressBookOldDefault);
    }
}
