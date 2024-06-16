package com.cafe.cafeback.service.impl;

import com.cafe.cafeback.dto.MobileCategoryDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.entity.MobilesCategory;
import com.cafe.cafeback.repository.MobileCategoryRepository;
import com.cafe.cafeback.service.MobileCategoryService;
import com.cafe.cafeback.util.ImageUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class MobileCategoryServiceImpl implements MobileCategoryService {

    private final MobileCategoryRepository mobileCategoryRepository;

    public MobileCategoryServiceImpl(MobileCategoryRepository mobileCategoryRepository) {
        this.mobileCategoryRepository = mobileCategoryRepository;
    }

    private static final Logger logger = Logger.getLogger(MobileCategoryServiceImpl.class);

    @Override
    public Response<String> addMobileCategory(MobileCategoryDTO mobileCategoryDTO , MultipartFile image) {


        try {
            MobilesCategory mobilesCategory = new MobilesCategory();
            mobilesCategory.setBrandName(mobileCategoryDTO.getBrandName());
            mobilesCategory.setModel(mobileCategoryDTO.getModel());
            mobilesCategory.setStorage(mobileCategoryDTO.getStorage());
            mobilesCategory.setMemory(mobileCategoryDTO.getMemory());
            mobilesCategory.setQuantity(mobileCategoryDTO.getQuantity());
            mobilesCategory.setPrice(mobileCategoryDTO.getPrice());
            mobilesCategory.setDescription(mobileCategoryDTO.getDescription());
            mobilesCategory.setManufactureYear(mobileCategoryDTO.getManufactureYear());
            mobilesCategory.setAddedOn(LocalDate.now());
            mobilesCategory.setUpdatedOn(null);

            try {
                byte[] compressedImage = ImageUtil.compressImage(image.getBytes());
                mobilesCategory.setImage(compressedImage);
            } catch (IOException e) {
                logger.error("Image not find or image type not match" + e);
                throw new RuntimeException(e);

            }

            mobileCategoryRepository.save(mobilesCategory);
            logger.info("New mobile category added to database");
            return new Response<>(1000, "New mobile category added to database", " Mobile category successfully added\n" + mobilesCategory);


        }catch (Exception e){
            logger.error("Error while adding mobile category" + e);
            System.out.println("Error"+e);
            return new Response<>(1001 , "Error in adding mobile category" , "Error :"+ e);
        }

    }
}
