package com.report.customreport.service.mapper;

import com.report.customreport.domain.JmdSales;
import com.report.customreport.domain.JmdUser;
import com.report.customreport.service.dto.JmdSalesDTO;
import com.report.customreport.service.dto.JmdUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link JmdSales} and its DTO {@link JmdSalesDTO}.
 */
@Mapper(componentModel = "spring")
public interface JmdSalesMapper extends EntityMapper<JmdSalesDTO, JmdSales> {
    @Mapping(target = "retailer", source = "retailer", qualifiedByName = "jmdUserId")
    JmdSalesDTO toDto(JmdSales s);

    @Named("jmdUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JmdUserDTO toDtoJmdUserId(JmdUser jmdUser);
}
