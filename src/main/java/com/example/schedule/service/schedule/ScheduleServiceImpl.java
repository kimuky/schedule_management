package com.example.schedule.service.schedule;

import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.schedule.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(int id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(int id,String userUid, String title, String content, String color) {

        Schedule beforeSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // 패스워드 대신 uid 로 구분
        if (!beforeSchedule.getUser_uid().equals(userUid)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 게시글 주인이 아님");
        }

        // 할일 작성자 말고 제목 내용 색깔
        if (title == null || color == null || content == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일부 비워져있음");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, title, content, color);

        if (updatedRow == 0) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 없습니다");
        }

        Schedule afterSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(afterSchedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedulePart(int id, ScheduleRequestDto dto) {
        Schedule beforeSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // 패스워드 대신 uid 로 구분
        if (!beforeSchedule.getUser_uid().equals(dto.getUser_uid())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 게시글 주인이 아님");
        }

        int updatedRow = scheduleRepository.updateScheduleTitle(id, dto);

        if (updatedRow == 0) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 없습니다");
        }
        Schedule afterSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(afterSchedule);
    }
}
