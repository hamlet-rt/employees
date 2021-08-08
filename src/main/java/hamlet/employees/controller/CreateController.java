package hamlet.employees.controller;

import hamlet.employees.entity.Country;
import hamlet.employees.entity.Employee;
import hamlet.employees.repository.CityRepository;
import hamlet.employees.repository.CountryRepository;
import hamlet.employees.repository.EmployeeRepository;
import hamlet.employees.validator.EmployeeValidator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/create")
public class CreateController {

    private final EmployeeValidator employeeValidator;

    private final CountryRepository countryRepository;

    private final EmployeeRepository employeeRepository;

    private final CityRepository cityRepository;


    public CreateController(EmployeeValidator employeeValidator,
                            CountryRepository countryRepository,
                            EmployeeRepository employeeRepository,
                            CityRepository cityRepository) {
        this.employeeValidator = employeeValidator;
        this.countryRepository = countryRepository;
        this.employeeRepository = employeeRepository;
        this.cityRepository = cityRepository;
    }

    @InitBinder
    public void initBinder(DataBinder dataBinder){
        dataBinder.addValidators(employeeValidator);
    }

    @GetMapping
    public String showCreatePage(Model model){
        model.addAttribute("employee", new Employee());
        Sort sort = Sort.by(Sort.Order.asc("name"));
        List<Country> countries = countryRepository.findAll(sort);
        model.addAttribute("countries", countries);
        return "create_page";
    }


    @PostMapping
    public String proceedCreateAction(@Valid Employee employee, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "create_page";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }
}
