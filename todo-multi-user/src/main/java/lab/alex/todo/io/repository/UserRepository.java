package lab.alex.todo.io.repository;

import org.springframework.data.repository.CrudRepository;

import lab.alex.todo.io.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	
	UserEntity findByPublicUserId(String id);
	

}
