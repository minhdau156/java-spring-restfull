package vn.hoidanit.jobhunter.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Resume;
import vn.hoidanit.jobhunter.domain.response.resume.ResCreateResumeDTO;
import vn.hoidanit.jobhunter.domain.response.resume.ResUpdateResumeDTO;
import vn.hoidanit.jobhunter.repository.ResumeRepository;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public boolean isExist(Long id) {
        return this.resumeRepository.existsById(id);
    }

    public Optional<Resume> fetchResumeById(Long id) {
        return this.resumeRepository.findById(id);
    }

    public ResCreateResumeDTO create(Resume r) {
        Resume currentResume = this.resumeRepository.save(r);

        ResCreateResumeDTO dto = new ResCreateResumeDTO();
        dto.setId(currentResume.getId());
        dto.setCreatedAt(currentResume.getCreatedAt());
        dto.setCreatedBy(currentResume.getEmail());
        return dto;
    }

    public ResUpdateResumeDTO update(Resume r) {
        Resume currentResume = this.resumeRepository.save(r);
        ResUpdateResumeDTO dto = new ResUpdateResumeDTO();
        dto.setUpdatedAt(currentResume.getUpdatedAt());
        dto.setUpdatedBy(currentResume.getEmail());
        return dto;
    }
}
