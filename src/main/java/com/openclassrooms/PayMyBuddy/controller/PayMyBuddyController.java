package com.openclassrooms.PayMyBuddy.controller;

import com.openclassrooms.PayMyBuddy.dto.TransactionsDTO;
import com.openclassrooms.PayMyBuddy.entity.Friends;
import com.openclassrooms.PayMyBuddy.entity.Transactions;
import com.openclassrooms.PayMyBuddy.repository.FriendsRepository;
import com.openclassrooms.PayMyBuddy.repository.TransactionsRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import com.openclassrooms.PayMyBuddy.dto.FriendsDTO;
import com.openclassrooms.PayMyBuddy.entity.User;
import com.openclassrooms.PayMyBuddy.security.CustomUserDetailsService;
import com.openclassrooms.PayMyBuddy.services.FriendsService;
import com.openclassrooms.PayMyBuddy.services.TransactionsService;
import com.openclassrooms.PayMyBuddy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class PayMyBuddyController {
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private FriendsService friendsService;
    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @GetMapping(value = "/")
    public String index(Model model){
        return home(model);
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginexit(Model model){
        return home(model);
    }

    @GetMapping(value = "/logout")
    public String logout(Model model){ return login(model); }

    @GetMapping(value = "/register")
    public String register(Model model){
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerexit(@RequestParam(name="name", required=false) String name,@RequestParam(name="email", required=false) String email, @RequestParam(name="password", required=false) String password, @RequestParam(name="iban", required=false) String iban, Model model){
        userService.saveUser(name, email, password, iban);
        return login(model);
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List <TransactionsDTO> transactions = transactionsService.getTransactionList(transactionsRepository.findAllBySenderIdOrReceiverIdOrderByCreatedAtDesc(user.getId(),user.getId()));
        if(transactions.size() > 5)transactions.subList(5, transactions.size()).clear();
        model.addAttribute("user",user);
        model.addAttribute("transactions",transactions);
        return "home";
    }

    @GetMapping(value = "/transactions")
    public String transactions(Model model) {
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List <TransactionsDTO> transactions = transactionsService.getTransactionList(transactionsRepository.findAllBySenderIdOrReceiverIdOrderByCreatedAtDesc(user.getId(),user.getId()));
        if(transactions.size() > 5)transactions.subList(5, transactions.size()).clear();
        List<FriendsDTO> friends = friendsService.getFriendsList(user.getId(), friendsRepository.findAllFriendIdByUserIdOrFriendId(user.getId()));
        model.addAttribute("user",user);
        model.addAttribute("transactions",transactions);
        model.addAttribute("friends",friends);
        return "transactions";
    }

    @PostMapping(value = "/sendtransaction")
    public String sendtransaction(@RequestParam(name="sendTo", required=true) Long sendTo,@RequestParam(name="description", required=false) String description,@RequestParam(name="amount", required=true) float amount, Model model) {
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(description == null)description="No description";
        if(transactionsService.sendTransaction(new Transactions(null,user.getId(),sendTo,description,amount,new Date()))){
            model.addAttribute("failure","Transaction fail");
        }
        return transactions(model);
    }

    @GetMapping(value = "/profile")
    public String profile(Model model) {
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List <TransactionsDTO> transactions = transactionsService.getTransactionList(transactionsRepository.findAllBySenderIdOrReceiverIdOrderByCreatedAtDesc(user.getId(),user.getId()));
        if(transactions.size() > 5)transactions.subList(5, transactions.size()).clear();
        model.addAttribute("transactions",transactions);
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("/edituser")
    public String editUser(@RequestParam(name="name", required=false) String name, @RequestParam(name="pass", required=false) String password, @RequestParam(name="iban", required=false) String iban, Model model){
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        userService.saveUser(name, user.getEmail(), password, iban);
        return profile(model);
    }

    @GetMapping(value = "/contact")
    public String contact(Model model) {
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<FriendsDTO> friends = friendsService.getFriendsList(user.getId(), friendsRepository.findAllFriendIdByUserIdOrFriendId(user.getId()));
        model.addAttribute("user",user);
       model.addAttribute("friends",friends);
        return "contact";
    }

    @GetMapping(value = "/addContact")
    public String addContact(Model model){
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user",user);
        return "addContact";
    }

    @PostMapping(value = "/addContact")
    public String addfriend(@RequestParam(name="email", required=false) String email, @RequestParam(name="friendid", required=false) Long friendId, Model model) {
        User user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<User> userList = new ArrayList();
        if(email != null){
            userList = userRepository.findAllUserByEmailLike(email);
            model.addAttribute("users",userList);
        }
        System.out.println(friendId);
        if(friendId != null){
            System.out.println(friendId);
            Friends friends = new Friends(null,user.getId(),friendId);
            friendsRepository.save(friends);
            return contact(model);
        }
        model.addAttribute("user",user);
        return "addContact";
    }
}
