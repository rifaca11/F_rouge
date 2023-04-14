package com.filrouge.designflow.spring.service;

import com.filrouge.designflow.spring.dto.SubflowDto;
import com.filrouge.designflow.spring.mapper.SubflowMapper;
import com.filrouge.designflow.spring.model.Subflow;
import com.filrouge.designflow.spring.exceptions.SpringFlowException;
import com.filrouge.designflow.spring.repository.SubflowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubflowService {

    private final SubflowRepository subflowRepository;
    private final SubflowMapper subflowMapper;

//  Method for saving a subflow
    @Transactional
    public SubflowDto save(SubflowDto subflowDto) {
        Subflow save = subflowRepository.save(subflowMapper.mapDtoToSubflow(subflowDto));
        subflowDto.setId(save.getId());
        return subflowDto;
    }

//  Method for getting all subflow
    @Transactional(readOnly = true)
    public List<SubflowDto> getAll() {
        return subflowRepository.findAll()
                .stream()
                .map(subflowMapper::mapSubflowToDto)
                .collect(toList());
    }

//   Method for getting one subflow
    public SubflowDto getSubflow(Long id) {
        Subflow subflow = subflowRepository.findById(id)
                .orElseThrow(() -> new SpringFlowException("No subflow found with ID - " + id));
        return subflowMapper.mapSubflowToDto(subflow);
    }
}
