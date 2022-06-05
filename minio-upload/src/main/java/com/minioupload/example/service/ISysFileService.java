package com.minioupload.example.service;

import org.springframework.web.multipart.MultipartFile;

public interface ISysFileService {
	    /**
	     * File upload interface
	     * 
	     * @param file Uploaded files
	     * @return Access address
	     * @throws Exception
	     */
	    public String uploadFile(MultipartFile file) throws Exception;

}
