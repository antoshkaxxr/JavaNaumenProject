package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Rating;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.RatingRepository;

import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingController {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/findByProductId")
    public List<Rating> findByProductId(@RequestParam Long productId) {
        return ratingRepository.findByProductId(productId);
    }

    @GetMapping("/findByCustomerId")
    public List<Rating> findByCustomerId(@RequestParam Long customerId) {
        return ratingRepository.findByCustomerId(customerId);
    }
}
