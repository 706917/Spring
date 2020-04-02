package alex.lab.photo.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import alex.lab.photo.app.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	

}
