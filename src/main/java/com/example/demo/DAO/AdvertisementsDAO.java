package com.example.demo.DAO;

import com.example.demo.domain.Advertisement.Advertisement;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AdvertisementsDAO extends CrudRepository<Advertisement, Long> {
    Iterable<Advertisement> findAll();
    Iterable<Advertisement> findAdvertisementsByTag(String tag);
    Iterable<Advertisement> findByPersonId(Long id);
    void deleteAllByPersonId(long id);
}
