package hamlet.employees.controller;

import hamlet.employees.entity.Employee;
import hamlet.employees.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(path = "/")
public class MainController {

    private final EmployeeRepository employeeRepository;

    public MainController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    @GetMapping
    public String showMainPage(@RequestParam Optional<String> searchString, Model model){
        List<Employee> employees;
        String searchStringVal = searchString.orElse("");
        if (!searchStringVal.isEmpty()){
            Matcher matcher = Pattern
                    .compile("^(\\p{L}+)\\s?(\\p{L}+)?\\s?(\\p{L}+)?$")
                    .matcher(searchStringVal);
            matcher.find();
            String firstName = matcher.group(1);
            String lastName = matcher.group(2);
            String middleName = matcher.group(3);
            employees = employeeRepository.findAll((root, query, builder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (firstName != null){
                    predicates.add(builder.like(root.get("firstName"), firstName + "%"));
                }
                if (lastName != null){
                    predicates.add(builder.like(root.get("lastName"), lastName + "%"));
                }
                if (middleName != null){
                    predicates.add(builder.like(root.get("middleName"), middleName + "%"));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            });
        } else {
            Sort sort = Sort.by(Sort.Order.asc("firstName"), Sort.Order.asc("lastName"));
            employees = employeeRepository.findAll(sort);

        }
        model.addAttribute("employees", employees);
        return "main_page";
    }
}
