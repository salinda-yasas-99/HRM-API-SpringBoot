package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.entity.Announcement;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Transactional
    public ResponseMessage createAnnouncement(Announcement announcement) {
        try {
            announcementRepository.save(announcement);
            return ResponseMessage.builder()
                    .message("Announcement created successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Announcement creation failed: " + e.getMessage())
                    .build();
        }
    }

    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    @Transactional
    public ResponseMessage updateAnnouncement(Long id, Announcement announcementDetails) {
        try {
            Announcement announcement = announcementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Announcement not found"));

            announcement.setSubject(announcementDetails.getSubject());
            announcement.setExpiresOn(announcementDetails.getExpiresOn());
            announcement.setContent(announcementDetails.getContent());

            announcementRepository.save(announcement);
            return ResponseMessage.builder()
                    .message("Announcement updated successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Announcement update failed: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage deleteAnnouncement(Long id) {
        try {
            Announcement announcement = announcementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Announcement not found"));

            announcementRepository.delete(announcement);
            return ResponseMessage.builder()
                    .message("Announcement deleted successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Announcement deletion failed: " + e.getMessage())
                    .build();
        }
    }
}
