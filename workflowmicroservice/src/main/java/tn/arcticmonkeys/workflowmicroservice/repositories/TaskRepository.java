package tn.arcticmonkeys.workflowmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
