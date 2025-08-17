package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Skill;
import vn.hoidanit.jobhunter.domain.Subscriber;
import vn.hoidanit.jobhunter.repository.SkillRepository;
import vn.hoidanit.jobhunter.repository.SubscriberRepository;

@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final SkillRepository skillRepository;

    public SubscriberService(SubscriberRepository subscriberRepository, SkillRepository skillRepository) {
        this.subscriberRepository = subscriberRepository;
        this.skillRepository = skillRepository;
    }

    public boolean isExistByEmail(String email) {
        return this.subscriberRepository.existsByEmail(email);
    }

    public Subscriber create(Subscriber s) {
        //check skill
        if (s.getSkills() != null) {
            List<Long> reqSkills = s.getSkills()
            .stream().map(x -> x.getId())
            .collect(Collectors.toList());

            List<Skill> dbSkills = this.skillRepository.findByIdIn(reqSkills);
            s.setSkills(dbSkills);
        }

        return this.subscriberRepository.save(s);
    }
    public Subscriber findById(long id) {
        Optional<Subscriber> subOptional = this.subscriberRepository.findById(id);
        if (subOptional.isPresent()) {
            return subOptional.get();
        }
        return null;
    }
    public Subscriber update(Subscriber subDB, Subscriber subRequest) {
        if (subRequest.getSkills() != null) {
            List<Long> reqSkills = subRequest.getSkills()
            .stream().map(x -> x.getId())
            .collect(Collectors.toList());

            List<Skill> dbSkills = this.skillRepository.findByIdIn(reqSkills);
            subDB.setSkills(dbSkills);
        }
        return this.subscriberRepository.save(subDB);
    }
}
