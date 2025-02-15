package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    List<AddressBook> findByCondition(AddressBook addressBook);

    AddressBook findById(Long id);

    void saveAddressBook(AddressBook addressBook);

    void updateAddressBook(AddressBook addressBook);

    void updateAllAddressBookIsDefault(AddressBook addressBook);

    void deleteAddressBook(Long id);
}
