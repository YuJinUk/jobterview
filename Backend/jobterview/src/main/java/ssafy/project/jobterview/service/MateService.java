package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.repository.MateRepository;

@Service
@RequiredArgsConstructor
public class MateService {
    private MateRepository mateRepository;

    public MateService(MateRepository mateRepository){
        this.mateRepository=mateRepository;
    }



}
