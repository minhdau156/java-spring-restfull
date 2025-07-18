package vn.hoidanit.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Skill;
import vn.hoidanit.jobhunter.domain.response.ResultPaginationDTO;
import vn.hoidanit.jobhunter.service.SkillService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/skills")
    @ApiMessage("Create a skill")
    public ResponseEntity<Skill> createSkill(@Valid @RequestBody Skill s) throws IdInvalidException {
        //check name 
        if (s.getName() != null && this.skillService.isNameExist(s.getName())) {
            throw new IdInvalidException("Skills name = " + s.getName() + " đã tồn tại");
        } 
        return ResponseEntity.status(HttpStatus.CREATED).body(this.skillService.createSkill(s));
    }

    @PutMapping("/skills")
    @ApiMessage("Update a skill") 
    public ResponseEntity<Skill> updateSkill(@Valid @RequestBody Skill s) throws IdInvalidException {
        Skill currSkill = this.skillService.fetchSkillById(s.getId());
        if (currSkill.getId() == null) {
            throw new IdInvalidException("Skill id = " + s.getId() + " không tồn tại");
        }
        //check name 
        if (s.getName() != null && this.skillService.isNameExist(s.getName())) {
            throw new IdInvalidException("Skills name = " + s.getName() + " đã tồn tại");
        }

        currSkill.setName(s.getName());
        return ResponseEntity.ok().body(this.skillService.updateSkill(currSkill));
    } 

    @GetMapping("/skills")
    @ApiMessage("fetch all skills")
    public ResponseEntity<ResultPaginationDTO> getAll(@Filter Specification<Skill> spec , Pageable pageable) {
        return ResponseEntity.ok().body(this.skillService.fetchAllSkills(spec, pageable));
    }


    @DeleteMapping("/skills/{id}")
    @ApiMessage("Delete a skill")
    public ResponseEntity<Void> delete(@PathVariable("id") long id ) throws IdInvalidException {
         Skill currSkill = this.skillService.fetchSkillById(id);
         if (currSkill == null) {
            throw new IdInvalidException("Skill id = " + id + " không tồn tại");
         }

         this.skillService.deleteSkill(id);
         return ResponseEntity.ok().body(null);
    }
}
