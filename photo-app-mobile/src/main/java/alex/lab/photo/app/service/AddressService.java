package alex.lab.photo.app.service;

import java.util.List;

import alex.lab.photo.app.shared.dto.AddressDto;

public interface AddressService {
	
	List<AddressDto> getAddresses(String userId);
	AddressDto getAddress(String adressId);

}
