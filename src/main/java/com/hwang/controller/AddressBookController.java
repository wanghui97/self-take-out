package com.hwang.controller;




import com.baomidou.mybatisplus.extension.api.ApiController;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.AddressBook;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.AddressBookService;
import com.hwang.utiltools.BaseContextUtil;
import com.hwang.utiltools.ResponseResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

/**
 * 地址管理(AddressBook)表控制层
 *
 * @author makejava
 * @since 2023-05-08 16:40:18
 */
@RestController
@RequestMapping("addressBook")
public class AddressBookController{
    /**
     * 服务对象
     */
    @Resource
    private AddressBookService addressBookService;
    /**
     * 查询当前用户地址列表
     * @return 新增结果
     */
    @GetMapping("/list")
    @PrintLogs(BusinessName = "查询地址列表")
    public ResponseResult getAddressBookList() {
       return addressBookService.getCurrentUserAddressBookList();
    }
    /**
     * 通过地址薄id查询地址详情
     * @return 新增结果
     */
    @GetMapping("/{id}")
    @PrintLogs(BusinessName = "通过地址薄id查询地址详情")
    public ResponseResult getAddressBookById(@PathVariable Long id) {
        return ResponseResult.okResult(addressBookService.getById(id));
    }
    /**
     * 查询默认地址
     * @return 新增结果
     */
    @GetMapping("/default")
    @PrintLogs(BusinessName = "查询默认地址")
    public ResponseResult getDefaultAddressBook() {
        return addressBookService.getDefaultAddressBook();
    }
    /**
     * 新增地址
     * @param addressBook 地址信息对象
     * @return 新增结果
     */
    @PostMapping
    @PrintLogs(BusinessName = "新增地址")
    public ResponseResult insertAddressBook (@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContextUtil.getCurrentId());
        boolean saveFlag = addressBookService.save(addressBook);
        if(saveFlag){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
    /**
     * 修改地址信息
     * @param addressBook 地址信息对象
     * @return 新增结果
     */
    @PutMapping
    @PrintLogs(BusinessName = "查询单个地址")
    public ResponseResult updateAddressBook(@RequestBody AddressBook addressBook) {
        boolean updateFlag = addressBookService.updateById(addressBook);
        if(updateFlag){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 修改默认地址信息
     * @param addressBook 地址薄信息
     * @return 修改结果
     */
    @PutMapping("/default")
    @PrintLogs(BusinessName = "修改默认地址信息")
    public ResponseResult updateDefaultAddressBook(@RequestBody AddressBook addressBook) {
        Long id = addressBook.getId();
        return addressBookService.updateDefaultAddressBook(id);
    }

    /**
     * 删除地址信息
     * @param ids 地址薄id
     * @return
     */
    @DeleteMapping
    @PrintLogs(BusinessName = "删除地址信息")
    public ResponseResult deleteAddressBook(Long ids) {
        boolean removeFlag = addressBookService.removeById(ids);
        if(removeFlag){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
}

