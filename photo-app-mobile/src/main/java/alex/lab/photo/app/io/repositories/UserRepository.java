package alex.lab.photo.app.io.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import alex.lab.photo.app.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository <UserEntity, Long> {
	
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String id);
	

}
  