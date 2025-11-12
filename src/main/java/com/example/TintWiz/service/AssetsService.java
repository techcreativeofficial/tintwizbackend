package com.example.TintWiz.service;

import com.example.TintWiz.repository.AssetsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetsService {

    private final AssetsRepository assetsRepository;

}
