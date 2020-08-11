package lab.alex.todo.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lab.alex.todo.io.entity.TodoEntity;


@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
	
	TodoEntity findByPublicTodoId (String id);

}
