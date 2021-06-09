package com.openclassrooms.PayMyBuddy.Controller;

import com.openclassrooms.PayMyBuddy.Dto.TransactionsDTO;
import com.openclassrooms.PayMyBuddy.Entity.Friends;
import com.openclassrooms.PayMyBuddy.Entity.Transactions;
import com.openclassrooms.PayMyBuddy.Repository.FriendsRepository;
import com.openclassrooms.PayMyBuddy.Repository.TransactionsRepository;
import com.openclassrooms.PayMyBuddy.Repository.UserRepository;
import com.openclassrooms.PayMyBuddy.Dto.FriendsDTO;
import com.openclassrooms.PayMyBuddy.Entity.User;
import com.openclassrooms.PayMyBuddy.Services.FriendsService;
import com.openclassrooms.PayMyBuddy.Services.TransactionsService;
import com.openclassrooms.PayMyBuddy.Services.UserService;
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
    private User user = new User();
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


    @GetMapping(value = "/")
    public String index(Model model){
        this.user = userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
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
        this.user = userService.saveUser(name, email, password, iban);
        return login(model);
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        List <TransactionsDTO> transactions = transactionsService.getTransactionList(transactionsRepository.findAllBySenderIdOrReceiverIdOrderByCreatedAtDesc(this.user.getId(),this.user.getId()));
        if(transactions.size() > 5)transactions.subList(5, transactions.size()).clear();
        model.addAttribute("user",this.user);
        model.addAttribute("transactions",transactions);
        return "home";
    }

    @GetMapping(value = "/transactions")
    public String transactions(Model model) {
        List <TransactionsDTO> transactions = transactionsService.getTransactionList(transactionsRepository.findAllBySenderIdOrReceiverIdOrderByCreatedAtDesc(user.getId(),user.getId()));
        if(transactions.size() > 5)transactions.subList(5, transactions.size()).clear();
        List<FriendsDTO> friends = friendsService.getFriendsList(this.user.getId(), friendsRepository.findAllFriendIdByUserIdOrFriendId(user.getId()));
        model.addAttribute("user",this.user);
        model.addAttribute("transactions",transactions);
        model.addAttribute("friends",friends);
        return "transactions";
    }

    @PostMapping(value = "/sendtransaction")
    public String sendtransaction(@RequestParam(name="sendTo", required=true) Long sendTo,@RequestParam(name="description", required=false) String description,@RequestParam(name="amount", required=true) float amount, Model model) {
        if(description == null)description="No description";
        if(transactionsService.sendTransaction(new Transactions(null,user.getId(),sendTo,description,amount,new Date()))){
            model.addAttribute("failure","Transaction fail");
        }
        return transactions(model);
    }

    @GetMapping(value = "/profile")
    public String profile(Model model) {
        List <TransactionsDTO> transactions = transactionsService.getTransactionList(transactionsRepository.findAllBySenderIdOrReceiverIdOrderByCreatedAtDesc(user.getId(),user.getId()));
        if(transactions.size() > 5)transactions.subList(5, transactions.size()).clear();
        model.addAttribute("transactions",transactions);
        model.addAttribute("user",this.user);
        return "profile";
    }

    @PostMapping("/edituser")
    public String editUser(@RequestParam(name="email", required=false) String email, @RequestParam(name="pass", required=false) String password, @RequestParam(name="iban", required=false) String iban, Model model){
        this.user = userService.saveUser(this.user.getName(), email, password, iban);
        return profile(model);
    }

    @GetMapping(value = "/contact")
    public String contact(Model model) {
        List<FriendsDTO> friends = friendsService.getFriendsList(this.user.getId(), friendsRepository.findAllFriendIdByUserIdOrFriendId(user.getId()));
        model.addAttribute("user",this.user);
       model.addAttribute("friends",friends);
        return "contact";
    }

    @GetMapping(value = "/addContact")
    public String addContact(Model model){
        model.addAttribute("user",this.user);
        return "addContact";
    }

    @PostMapping(value = "/addContact")
    public String addfriend(@RequestParam(name="email", required=false) String email, @RequestParam(name="friendid", required=false) Long friendId, Model model) {
        List<User> userList = new ArrayList();
        if(email != null){
            userList = userRepository.findAllUserByEmailLike(email);
            model.addAttribute("users",userList);
        }
        System.out.println(friendId);
        if(friendId != null){
            System.out.println(friendId);
            Friends friends = new Friends(null,this.user.getId(),friendId);
            friendsRepository.save(friends);
            return contact(model);
        }
        model.addAttribute("user",this.user);
        return "addContact";
    }
}
