package com.moracle.webticketsystem.model;

import com.moracle.webticketsystem.model.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by dmitry on 8/14/2016.
 */
public class Utils {

    public static Attachment createAttachment(String realPath, String urlPath, MultipartFile attachedFile) throws IOException {
        Attachment attachment = new Attachment();
        String filePath = realPath;
        String fileName = attachedFile.getOriginalFilename().replace(' ', '_');
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (Files.exists(Paths.get(filePath + "/" + uuid)));
        filePath = filePath + "/" + uuid + "/" + fileName;
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(attachedFile.getBytes());
        }
        attachment.setPath(urlPath + "/" + uuid + "/" + fileName);
        attachment.setSize((int) attachedFile.getSize());
        return attachment;
    }
}
