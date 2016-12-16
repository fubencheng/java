package com.net.lnk.cglib;

public class AddressServiceImpl implements AddressService {

	public UserAddress getUserAddress(String userName) {
		UserAddress address = new UserAddress();
		address.setAddressId(1L);
		address.setAddress("shang hai");
		address.setUserName(userName);

		return address;
	}

}
