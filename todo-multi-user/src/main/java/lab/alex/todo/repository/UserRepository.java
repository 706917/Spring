package lab.alex.todo.repository;

import org.springframework.data.repository.CrudRepository;

import lab.alex.todo.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	

}
