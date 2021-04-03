package com.keb.club_pila.dto.course;

import com.keb.club_pila.model.entity.course.Course;
import com.keb.club_pila.model.entity.course.EquipmentType;
import com.keb.club_pila.model.entity.course.Teacher;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public class CourseDto {
    @Getter
    @NoArgsConstructor
    public static class CourseResponseDto {
        private Long id;
        private String title;
        private String content;
        private String teacher_name;
        private EquipmentType equipmentType;

        public CourseResponseDto(Course entity) { //entitiy->dto 로 convert!!
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.teacher_name = entity.getTeachers().getName();
            this.equipmentType=entity.getEquipmentType();
        }


    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseSaveRequestDto {
        private String title;
        private String content;
        private Long teacher_id;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime courseDateTime;
        private EquipmentType equipmentType;
        public Course toEntity(Teacher teacher) {
            return Course.builder()
                    .title(title)
                    .content(content)
                    .courseDateTime(courseDateTime)
                    .teachers(teacher)
                    .equipmentType(equipmentType)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class CourseUpdateDto {
        private String title;
        private String content;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime courseDateTime;
        private EquipmentType equipmentType;
        @Builder
        public CourseUpdateDto(String title, String content, LocalDateTime courseDateTime, EquipmentType equipmentType) {
            this.title = title;
            this.content = content;
            this.courseDateTime = courseDateTime;
            this.equipmentType=equipmentType;
        }
    }
}
