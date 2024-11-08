package com.example.schedule.service.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.BadRequestException;
import com.example.schedule.exception.ForbiddenException;
import com.example.schedule.exception.NotFoundException;
import com.example.schedule.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {

        int scheduledRow = scheduleRepository.createSchedule(dto);

        if (scheduledRow == 0) {
            throw new NotFoundException();
        }

        return new ScheduleResponseDto(dto);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(int id) {
        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(NotFoundException::new);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(int id, String userUid, String title, String content, String color) {

        Schedule beforeSchedule = scheduleRepository.findScheduleById(id)
                .orElseThrow(NotFoundException::new);

        // 패스워드 대신 uid 로 비교
        if (!beforeSchedule.getUser_uid().equals(userUid)) {
            throw new ForbiddenException();
        }

        // 할일 작성자 대신 제목 내용 색깔
        if (title == null || color == null || content == null) {
            throw new BadRequestException();
        }

        int updatedRow = scheduleRepository.updateSchedule(id, title, content, color);

        if (updatedRow == 0) {
            throw new NotFoundException();
        }

        Schedule afterSchedule = scheduleRepository.findScheduleById(id).orElseThrow(NotFoundException::new);

        return new ScheduleResponseDto(afterSchedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedulePart(int id, ScheduleRequestDto dto) {
        Schedule beforeSchedule = scheduleRepository.findScheduleById(id)
                .orElseThrow(NotFoundException::new);

        // 패스워드 대신 uid 로 구분
        if (!beforeSchedule.getUser_uid().equals(dto.getUser_uid())) {
            throw new ForbiddenException();
        }

        int updatedRow = scheduleRepository.updateScheduleTitle(id, dto);

        if (updatedRow == 0) {
            throw new NotFoundException();
        }
        Schedule afterSchedule = scheduleRepository.findScheduleById(id).orElseThrow(NotFoundException::new);

        return new ScheduleResponseDto(afterSchedule);
    }

    @Override
    public void deleteSchedule(int id, String userUid) {
        Schedule beforeSchedule = scheduleRepository.findScheduleById(id)
                .orElseThrow(NotFoundException::new);

        if (!beforeSchedule.getUser_uid().equals(userUid)) {
            throw new ForbiddenException();
        }

        int updatedRow = scheduleRepository.deleteScheduleTitle(id);

        if (updatedRow == 0) {
            throw new NotFoundException();
        }
    }

    @Override
    public List<ScheduleResponseDto> pagination(int pageNum, int pageSize) {

        return scheduleRepository.findPageSchedules(pageNum, pageSize);
    }
}
