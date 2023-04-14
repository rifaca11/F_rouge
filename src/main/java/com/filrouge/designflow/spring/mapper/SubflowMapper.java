package com.filrouge.designflow.spring.mapper;

import com.filrouge.designflow.spring.dto.SubflowDto;
import com.filrouge.designflow.spring.model.Post;
import com.filrouge.designflow.spring.model.Subflow;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubflowMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subflow.getPosts()))")
    SubflowDto mapSubflowToDto(Subflow subflow);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subflow mapDtoToSubflow(SubflowDto subflowDto);
}
