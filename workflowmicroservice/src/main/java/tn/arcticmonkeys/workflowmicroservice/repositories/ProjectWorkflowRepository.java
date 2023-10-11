package tn.arcticmonkeys.workflowmicroservice.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.arcticmonkeys.workflowmicroservice.entities.ProjectWorkflow;

import java.util.List;

@Repository
public interface ProjectWorkflowRepository extends JpaRepository<ProjectWorkflow,Long> {
    @Query("SELECT DISTINCT(t.userId) from Task t where t.project.id=:projectId")
    public List<Long> retrieveContributedUsers(@Param("projectId") long projectId);
    @Query("SELECT sum(t.price) from Task t where t.project.id=:projectId")
    public double retrieveProjectCost(@Param("projectId") long projectId);
    public List<ProjectWorkflow> findProjectWorkflowByCompanyId(long idCompany);
    @Query("SELECT t.project.id from Task t where t.id=:idTask")
    public long findProjectWorkflowByTaskId(long idTask);
}
