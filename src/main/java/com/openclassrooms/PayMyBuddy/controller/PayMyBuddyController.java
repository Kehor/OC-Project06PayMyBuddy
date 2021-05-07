package com.openclassrooms.PayMyBuddy.controller;

import com.openclassrooms.PayMyBuddy.dao.FriendsDAO;
import com.openclassrooms.PayMyBuddy.dao.TransactionsDAO;
import com.openclassrooms.PayMyBuddy.dao.UserDAO;
import com.openclassrooms.PayMyBuddy.entity.Friends;
import com.openclassrooms.PayMyBuddy.entity.Transactions;
import com.openclassrooms.PayMyBuddy.entity.User;
import com.openclassrooms.PayMyBuddy.services.TransactionsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class PayMyBuddyController {
    private User user = new User();
    private UserDAO userDao = new UserDAO();
    private FriendsDAO friendsDAO = new FriendsDAO();
    private TransactionsDAO transactionsDao = new TransactionsDAO();
    int userid = 4;
    @Autowired
    private TransactionsService transactionsService;

    private static final Logger logger = LogManager.getLogger("Debug");

    @GetMapping(value = "/")
    public String index(Model model) {
        this.user = userDao.getUserById(userid);
        model.addAttribute("name",user.getName());
        return "index";
    }

    @GetMapping(value = "/transactions")
    public String transactions(Model model) {
        List <Transactions> transactions = transactionsDao.getTransaction(userid,true);
        Friends friends = friendsDAO.getFriends(userid);
        model.addAttribute("transactions",transactions);
        model.addAttribute("friends",friends);
        return "transactions";
    }

    @PostMapping(value = "/sendtransaction")
    public String sendtransaction(@RequestParam(name="sendTo", required=true) int sendTo,@RequestParam(name="description", required=false) String description,@RequestParam(name="amount", required=true) float amount, Model model) {
        if(description == null)description="No description";
        if(amount > 0)transactionsService.makeTransaction(userid, sendTo, description, amount);
        return transactions(model);
    }
    @PostMapping(value = "/contact")
    public String friendlist(@RequestParam(name="email", required=true) String email, Model model) {
        User user = new User();
        user = userDao.getUserByEmail(email);
        if(user != null) {
            model.addAttribute("user",user);
        }
        return "contact";
    }
}
