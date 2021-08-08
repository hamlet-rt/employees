package hamlet.employees.validator;

import hamlet.employees.entity.Employee;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Employee employee = (Employee) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "Имя обязательно для заполениня");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Фамилия обязательна для заполениня");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "", "Номер обязателен для заполениня");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city.id", "", "Город обязателен для заполениня");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "", "Адрес обязателен для заполениня");
        if (employee.getPhoneNumber().matches("\\+\\d\\s\\(\\d{3}\\)\\s\\d{3}-\\d{2}-\\d{2}")){
            errors.rejectValue("phoneNumber", "", "Номер телефона должен быть в формате +# (###) ###-##-##");
        }
    }
}
