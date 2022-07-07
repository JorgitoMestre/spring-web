package com.devs4j.user.controllers;

import com.devs4j.user.model.User;
import com.devs4j.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//definicion del recurso
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userServices;
    @GetMapping //-->seutiliza para obtener todos los objetos (listar)

    //metodo http+recurso-->handler methods
    public ResponseEntity<List<User>> getUsers(@RequestParam(value="startWhith",required=false) String startWhith){
      return new ResponseEntity<List<User>>(userServices.getUsers(startWhith),HttpStatus.OK);
    }

    @GetMapping(value="/{user_name}")//-->se utiliza para obtener un objeto a partir de un parametro (listar dado un id)
    // el @PathVariable("user_name") indica q el parametro q se paso en la url
    // a traves de @GetMapping(value="/{user_name}") se va a inyectar como
    // parametro de la funcion
    public ResponseEntity<User> getUser(@PathVariable("user_name") String user_name){
        return new ResponseEntity<User>(userServices.getUserByUserName(user_name),HttpStatus.OK);
    }
    @PostMapping //--> se utiliza para insertar nuevos objetos, en este caso usuarios (insertar)
    //de esta forma creo un nuevo recurso en el sistema,
    // antes de la funcion debo siempre poner la anotacion @GetMapping
    // y delante del parametro @RequestBody,
    // si no pongo el RequestBody el dato se envia nulo
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<User>(userServices.createUser(user),HttpStatus.CREATED);
    }

    @PutMapping(value="/{user_name}")
    //esta es la forma de hacer los updates
    public ResponseEntity<User> updateUser(@PathVariable("user_name") String user_name, @RequestBody User user){
        return new ResponseEntity<User>(userServices.updateUser(user,user_name),HttpStatus.OK);
    }

    @DeleteMapping(value="/{user_name}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_name") String user_name){
        userServices.deleteUser(user_name);
        return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
    }
//---------uso de query param
  /*  @GetMapping("/queryp") //-->seutiliza para obtener todos los objetos (listar)
    //metodo http+recurso-->handler methods
    public ResponseEntity<List<User>> getUsers1(@RequestParam("startWhith") String startWhith){
        return new ResponseEntity<List<User>>(userServices.getUsers1(startWhith),HttpStatus.OK);
    }*/


}
