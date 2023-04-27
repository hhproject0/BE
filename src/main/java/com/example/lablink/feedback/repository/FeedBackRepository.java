package com.example.lablink.feedback.repository;

import com.example.lablink.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<Feedback,Long> {

    List<Feedback> findAllByStudyId(Long id);
}
