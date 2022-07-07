package com.devs4j.user.services;

import com.devs4j.user.model.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private Faker faker;
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init(){
        for (int i=0; i<100; i++)
          users.add(new User(faker.funnyName().name(), faker.name().username(),faker.dragonBall().character()));
    }

    public List<User> getUsers(String startWith) {
        if(startWith!=null){
            return users.stream().filter(u->u.getUsername().startsWith(startWith)).collect(Collectors.toList());
        }else{
            return users;
        }
    }

    public User getUserByUserName(String user_name){
        //busca a user_name dentro de la lista, si lo encuentra devuelve al usuario
        // y si no lo encuentra devuelve user not found
        return users.stream().filter(u -> u.getUsername().equals(user_name)).findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User %s not found", user_name)));
    }

    public User createUser(User user){
        if(users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))){
            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("User %s alredy exist",user.getUsername()));
        }else{
            users.add(user);
        }
        return user;
    }

    public User updateUser(User user, String user_name){
        User userToBeUpdate=getUserByUserName(user_name);
        userToBeUpdate.setNickName(user.getNickName());
        userToBeUpdate.setUsername(user.getUsername());
        userToBeUpdate.setPassword(user.getPassword());
        return userToBeUpdate;
    }

    public void deleteUser(String user_name){
        User userToBeDelete=getUserByUserName(user_name);
        users.remove(userToBeDelete);
    }


   /* public List<User> getUsers1(String startWith) {
        if(startWith!=null){
            return users.stream().filter(u->u.getUsername().startsWith(startWith)).collect(Collectors.toList());
        }else{
            return users;
        }
    }*/
}
