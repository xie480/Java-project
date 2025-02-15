package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    List<AddressBook> findAll();

    List<AddressBook> findDefault(AddressBook addressBook);

    AddressBook findById(Long id);

    void saveAddressBook(AddressBook addressBook);

    void updateAddressBook(AddressBook addressBook);

    void updateAddressBookIsDefault(AddressBook addressBook);

    void deleteAddressBook(Long id);
}
