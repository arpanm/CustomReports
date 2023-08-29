package com.report.customreport.service.mapper;

import com.report.customreport.domain.JmdUser;
import com.report.customreport.service.dto.JmdUserDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link JmdUser} and its DTO {@link JmdUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface JmdUserMapper extends EntityMapper<JmdUserDTO, JmdUser> {
    @Mapping(target = "retailers", source = "retailers", qualifiedByName = "jmdUserIdSet")
    JmdUserDTO toDto(JmdUser s);

    @Mapping(target = "removeRetailer", ignore = true)
    JmdUser toEntity(JmdUserDTO jmdUserDTO);

    @Named("jmdUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JmdUserDTO toDtoJmdUserId(JmdUser jmdUser);

    @Named("jmdUserIdSet")
    default Set<JmdUserDTO> toDtoJmdUserIdSet(Set<JmdUser> jmdUser) {
        return jmdUser.stream().map(this::toDtoJmdUserId).collect(Collectors.toSet());
    }
}
