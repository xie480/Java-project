package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@Slf4j
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping("/list")
    public Result<List<AddressBook>> findAll(){
        log.info("查询所有地址");
        return Result.success(addressBookService.findAll());
    }

    @GetMapping("/default")
    public Result<AddressBook> findDefault(@RequestBody AddressBook addressBook){
        log.info("查询默认地址");
        List<AddressBook> list =  addressBookService.findDefault(addressBook);
        if(list != null && list.size() == 1){
            return Result.success(list.get(0));
        }
        return Result.error("没有默认地址");
    }

    @GetMapping("/{id}")
    public Result<AddressBook> findById(@PathVariable Long id){
        log.info("查询地址");
        AddressBook addressBook1 = addressBookService.findById(id);
        return Result.success(addressBook1);
    }

    @PostMapping
    public Result saveAddressBook(@RequestBody AddressBook addressBook){
        log.info("新增地址");
        addressBookService.saveAddressBook(addressBook);
        return Result.success();
    }

    @PutMapping
    public Result updateAddressBook(@RequestBody AddressBook addressBook){
        log.info("修改地址");
        addressBookService.updateAddressBook(addressBook);
        return Result.success();
    }

    @PutMapping("/default")
    public Result setDefault(@RequestBody AddressBook addressBook){
        log.info("设置默认地址");
        addressBookService.updateAddressBookIsDefault(addressBook);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteAddressBook(@RequestParam("id") Long id){
        log.info("删除地址");
        addressBookService.deleteAddressBook(id);
        return Result.success();
    }
}
