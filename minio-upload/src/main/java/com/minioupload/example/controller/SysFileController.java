package com.minioupload.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minioupload.example.service.ISysFileService;


@RestController
public class SysFileController {
	
	@Autowired
    private ISysFileService sysFileService;
	private static final Logger LOGGER=LoggerFactory.getLogger(SysFileController.class);
	
    /**
     * File upload request
     */
    @PostMapping("upload")
    public Map<String, Object> upload(MultipartFile file)
    {
        Map<String, Object> map = new HashMap<>();
        try
        {
            // Upload and return access address
            String url = sysFileService.uploadFile(file);
            map.put("status", "success");
            map.put("data", url);
            return map;
        }
        catch (Exception e)
        {
        	LOGGER.error("Failed to upload file", e);
            map.put("status", "error");
            map.put("msg", e.getMessage());
            return map;
        }
    }

}
