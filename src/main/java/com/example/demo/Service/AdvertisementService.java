package com.example.demo.Service;

import com.example.demo.DAO.AdvertisementsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementsDAO advertisementsDAO;

}
