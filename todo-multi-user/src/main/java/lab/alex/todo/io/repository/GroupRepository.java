package lab.alex.todo.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lab.alex.todo.io.entity.GroupEntity;


@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {

}
