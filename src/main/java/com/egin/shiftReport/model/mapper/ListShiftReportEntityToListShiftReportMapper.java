package com.egin.shiftReport.model.mapper;

import com.egin.shiftReport.model.ShiftReport;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListShiftReportEntityToListShiftReportMapper {

    public List<ShiftReport> toShiftReportList(final List<ShiftReportEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(ShiftReportEntityToShiftReportMapper::toShiftReport)
                .collect(Collectors.toList());
    }

}

