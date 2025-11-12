package com.example.TintWiz.controller;

import com.example.TintWiz.service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/assets")
public class AssetsController {

    @Autowired
    private AssetsService assetsService;

}
