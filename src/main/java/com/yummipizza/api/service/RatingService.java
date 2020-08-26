package com.yummipizza.api.service;

import com.yummipizza.api.domain.Rating;
import com.yummipizza.api.repository.RatingRepository;
import com.yummipizza.api.service.dto.RatingDTO;
import com.yummipizza.api.service.mapper.RatingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Rating}.
 */
@Service
@Transactional
public class RatingService {

    private final Logger log = LoggerFactory.getLogger(RatingService.class);

    private final RatingRepository ratingRepository;

    private final RatingMapper ratingMapper;

    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    /**
     * Save a rating.
     *
     * @param ratingDTO the entity to save.
     * @return the persisted entity.
     */
    public RatingDTO save(RatingDTO ratingDTO) {
        log.debug("Request to save Rating : {}", ratingDTO);
        Rating rating = ratingMapper.toEntity(ratingDTO);
        rating = ratingRepository.save(rating);
        return ratingMapper.toDto(rating);
    }

    /**
     * Get all the ratings.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RatingDTO> findAll() {
        log.debug("Request to get all Ratings");
        return ratingRepository.findAll().stream()
            .map(ratingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one rating by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RatingDTO> findOne(Long id) {
        log.debug("Request to get Rating : {}", id);
        return ratingRepository.findById(id)
            .map(ratingMapper::toDto);
    }

    /**
     * Delete the rating by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Rating : {}", id);
        ratingRepository.deleteById(id);
    }
}
