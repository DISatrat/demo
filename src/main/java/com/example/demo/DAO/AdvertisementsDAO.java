package com.example.demo.DAO;

import com.example.demo.domain.Advertisement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdvertisementsDAO extends CrudRepository<Advertisement,Long> {
Iterable<Advertisement> findAll();
//Optional<Advertisement> findAdvertisementByText();
Advertisement save(Advertisement advertisement);
}
