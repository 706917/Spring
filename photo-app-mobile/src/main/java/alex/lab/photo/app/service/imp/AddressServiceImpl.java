package alex.lab.photo.app.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alex.lab.photo.app.io.entity.AddressEntity;
import alex.lab.photo.app.io.entity.UserEntity;
import alex.lab.photo.app.io.repositories.AddressRepository;
import alex.lab.photo.app.io.repositories.UserRepository;
import alex.lab.photo.app.service.AddressService;
import alex.lab.photo.app.shared.dto.AddressDto;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public List<AddressDto> getAddresses(String userId) {
		
		List<AddressDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity ==null) return returnValue;
		
		// List<AddressEntity> addressEntity = userEntity.getAddresses();
		Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
		
		for(AddressEntity address: addresses) {
			returnValue.add(modelMapper.map(address, AddressDto.class));
		}
				
		return returnValue;
	}

	@Override
	public AddressDto getAddress(String addressId) {
		
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		//if(addressEntity==null) return null;
		
		AddressDto returnValue = new ModelMapper().map(addressEntity, AddressDto.class);
		
		return returnValue;
	}

}
