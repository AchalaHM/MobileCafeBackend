package com.cafe.cafeback.controller;

import com.cafe.cafeback.dto.MobileCategoryDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.service.MobileCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:3000")
public class MobileCategoryController {

    private final MobileCategoryService mobileCategoryService;

    public MobileCategoryController(MobileCategoryService mobileCategoryService) {
        this.mobileCategoryService = mobileCategoryService;
    }


    @PostMapping("/addMobileCategory")
    public ResponseEntity<Response<String>> addMobileCategory(
            @ModelAttribute MobileCategoryDTO mobileCategoryDTO,
            @RequestParam("image") MultipartFile image) {

        return ResponseEntity.ok(mobileCategoryService.addMobileCategory(mobileCategoryDTO, image));

    }

}
