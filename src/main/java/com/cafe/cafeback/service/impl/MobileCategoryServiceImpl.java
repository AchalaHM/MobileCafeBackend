package com.cafe.cafeback.service.impl;

import com.cafe.cafeback.dto.MobileCategoryDTO;
import com.cafe.cafeback.dto.MobileDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.entity.MobilesCategory;
import com.cafe.cafeback.repository.MobileCategoryRepository;
import com.cafe.cafeback.service.MobileCategoryService;
import com.cafe.cafeback.util.ImageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Response<List<MobileDTO>> getAllMobiles() {
        try {
            List<MobileDTO> mobileDTOS = new ArrayList<>();
            List<MobilesCategory> mobilesCategories = mobileCategoryRepository.findAll();

            for (MobilesCategory mobilesCategory: mobilesCategories) {
                MobileDTO dto = new MobileDTO();
                dto.setId(mobilesCategory.getId());
                dto.setBrandName(mobilesCategory.getBrandName());
                dto.setModel(mobilesCategory.getModel());
                dto.setStorage(mobilesCategory.getStorage());
                dto.setMemory(mobilesCategory.getMemory());
                dto.setPrice(mobilesCategory.getPrice());
                dto.setDescription(mobilesCategory.getDescription());
                dto.setManufactureYear(mobilesCategory.getManufactureYear());

                byte[] decompressedImage = ImageUtil.decompressImage(mobilesCategory.getImage());
                dto.setImage(decompressedImage);

                mobileDTOS.add(dto);
            }

            logger.info("Mobile list get successfully");
            return new Response<>(1000, "mobile list get successfully" , mobileDTOS);

        } catch (Exception e){
            logger.error("Error while getting mobile list");
            return new Response<>(1001, "Error while getting mobile list" , null);
        }
    }

    @Override
    public Response<String> updateMobiles(MobileCategoryDTO mobileCategoryDTO) {
        try{
            Optional<MobilesCategory> existingCategory = mobileCategoryRepository.findById(mobileCategoryDTO.getId());

            if (existingCategory.isEmpty()){
                return new Response<>(1002, "Category not found" , null);
            }

            MobilesCategory mobilesCategory = existingCategory.get();
            mobilesCategory.setUpdatedOn(LocalDate.now());
            if(mobileCategoryDTO.getPrice() != 0){
                mobilesCategory.setPrice(mobileCategoryDTO.getPrice());
            }

            if (mobileCategoryDTO.getQuantity() != 0){
                mobilesCategory.setQuantity(mobilesCategory.getQuantity() + mobileCategoryDTO.getQuantity());
            }

            if(mobileCategoryDTO.getDescription() != null){
                mobilesCategory.setDescription(mobileCategoryDTO.getDescription());
            }


            mobileCategoryRepository.save(mobilesCategory);
            logger.info("Update successfully" + mobilesCategory);
            return new Response<>(1000, "Update successfully" , "Updated things"+ mobilesCategory);
        } catch (Exception e){
            return new Response<>(1001 , "Error occured while updating mobile details", null);
        }
    }


}
