package com.cafe.cafeback.service;

import com.cafe.cafeback.dto.MobileCategoryDTO;
import com.cafe.cafeback.dto.MobileDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.entity.MobilesCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MobileCategoryService {
    Response<String> addMobileCategory(MobileCategoryDTO mobileCategoryDTO , MultipartFile imageFile);

    Response<List<MobileDTO>> getAllMobiles();

    Response<String> updateMobiles( MobileCategoryDTO mobileCategoryDTO);
}
