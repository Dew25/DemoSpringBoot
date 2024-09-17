package ee.ivkhkdev.demo.controller;

import ee.ivkhkdev.demo.entity.MyUser;
import ee.ivkhkdev.demo.service.MyUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final MyUserService myUserService;
    @Autowired
    public UserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/")
    public String begin(Model model) {
        return "index";
    }

    @GetMapping("/user/form")
    public String showUserForm(Model model) {
        model.addAttribute("myUser", new MyUser());
        return "addnewuser";
    }

    @PostMapping("/user/save")
    public String saveUser(@Valid @ModelAttribute MyUser myUser, BindingResult result, Model model) {
        // Проверяем, есть ли ошибки валидации
        if (result.hasErrors()) {
            // Если есть ошибки, возвращаем форму с сообщением об ошибках
            model.addAttribute("myUser", myUser);
            return "userForm"; // Возвращаем на ту же страницу с формой
        }

        // Вызов метода сервиса для сохранения пользователя
        myUserService.saveUser(myUser);

        // Перенаправляем на список пользователей после успешного сохранения
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        // Получаем список пользователей и передаем в модель для отображения
        model.addAttribute("users", myUserService.getAllUsers());
        return "users";
    }
}
