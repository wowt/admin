package com.hongcheng.fruitmall.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static String uploadFile(MultipartFile file, String path) throws Exception {
        String fName = file.getOriginalFilename();
        String t_fName = fName.substring(fName.lastIndexOf("."));
        long l = System.currentTimeMillis();
        fName = l+t_fName;
        writeOut(file.getBytes(),path,fName);
        return fName;
    }

    private static void writeOut(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
