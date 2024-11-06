package com.example.schedule.service.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.repository.schedule.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {

        // NOT NULL 에 대한 확인
        if (dto.getUser_uid() == null || dto.getTitle() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디 혹은 제목이 비워져 있습니다.");
        }

        // TODO 외래키가 올바른지에 대한 예외처리 해야함
        int scheduledRow = scheduleRepository.createSchedule(dto);

        if (scheduledRow == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일부 비워져있음");
        }

        return new ScheduleResponseDto(dto.getUser_uid());
    }
}
