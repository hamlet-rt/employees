package hamlet.employees.controller;

import hamlet.employees.entity.City;
import hamlet.employees.repository.CityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/countries")
public class CountryController {

    private final CityRepository cityRepository;

    public CountryController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @GetMapping(path = "/{countryId}/cities")
    public List<City> getCityListByCountryId(@PathVariable long countryId){
        return cityRepository.findAllByCountryId(countryId);
    }
}
