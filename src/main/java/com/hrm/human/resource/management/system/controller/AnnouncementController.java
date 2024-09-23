package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.entity.Announcement;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseMessage createAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.createAnnouncement(announcement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable Long id) {
        try {
            Announcement announcement = announcementService.getAnnouncementById(id);
            return ResponseEntity.ok(announcement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseMessage.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @PutMapping("/{id}")
    public ResponseMessage updateAnnouncement(@PathVariable Long id, @RequestBody Announcement announcementDetails) {
        return announcementService.updateAnnouncement(id, announcementDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteAnnouncement(@PathVariable Long id) {
        return announcementService.deleteAnnouncement(id);
    }
}
