package vn.hoidanit.jobhunter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.Skill;


@Repository

public interface SkillRepository extends JpaRepository<Skill, Long>, JpaSpecificationExecutor<Skill>{
    boolean existsByName(String name);

	List<Skill> findByIdIn(List<Long> reqSkills);
}
