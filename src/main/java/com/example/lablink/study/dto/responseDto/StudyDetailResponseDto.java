package com.example.lablink.study.dto.responseDto;

import com.example.lablink.category.entity.Category;
import com.example.lablink.study.entity.Study;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class StudyDetailResponseDto {
    private final Long id;
    private final String title;
    private final String studyInfo;
    private final String studyPurpose;
    private final String studyAction;
    private final Long subjectCount;
    private final String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime date;
    private final String address;
    private final int pay;
    private final String subjectGender;
    private final String subjectAge;
    private final int repearCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime endDate;

    private final String imageURL;
    private final boolean isBookmarked;

    public StudyDetailResponseDto(Study study, Category category, boolean isBookmarked) {
        this.id = study.getId();
        this.title = study.getTitle();
        this.studyInfo = study.getStudyInfo();
        this.studyPurpose = study.getStudyPurpose();
        this.studyAction = study.getStudyAction();
        this.subjectCount = study.getSubjectCount();
        this.category = category.getCategory();
        this.date = study.getDate();
        this.address = study.getAddress();
        this.pay = study.getPay();
        this.subjectGender = study.getSubjectGender();
        this.subjectAge = study.getSubjectAge();
        this.repearCount = study.getRepearCount();
        this.endDate = study.getEndDate();
        this.imageURL = study.getImageURL();
        this.isBookmarked = isBookmarked;
    }
}
