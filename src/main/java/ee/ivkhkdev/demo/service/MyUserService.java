package ee.ivkhkdev.demo.service;

import ee.ivkhkdev.demo.entity.MyUser;
import ee.ivkhkdev.demo.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserService {
    private final MyUserRepository myUserRepository;

    @Autowired
    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }
    public MyUser saveUser(MyUser myUser) {
        // Дополнительная логика перед сохранением (например, хеширование пароля)
        // Проверка на уникальность пользователя или валидация данных
        return myUserRepository.save(myUser);
    }

    public List<MyUser> getAllUsers() {
        return myUserRepository.findAll();
    }

    public MyUser getUserById(Long id) {
        return myUserRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        myUserRepository.deleteById(id);
    }
}
