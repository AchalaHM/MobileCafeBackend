package com.cafe.cafeback.service;

import com.cafe.cafeback.dto.MobileCategoryDTO;
import com.cafe.cafeback.dto.Response;
import org.springframework.web.multipart.MultipartFile;

public interface MobileCategoryService {
    Response<String> addMobileCategory(MobileCategoryDTO mobileCategoryDTO , MultipartFile imageFile);
}
