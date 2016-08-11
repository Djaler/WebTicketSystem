package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Attachment;
import com.moracle.webticketsystem.model.repository.AttachmentRepository;
import com.moracle.webticketsystem.model.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmitry on 8/10/2016.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }
}
