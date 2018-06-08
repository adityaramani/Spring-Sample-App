package com.sample.twitter.controllers;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/connect")
public class FlowController extends ConnectController {

    public FlowController(ConnectionFactoryLocator connectionFactoryLocator,
                                       ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected String connectedView(String providerId) {
        System.out.println("Provider iD =  " + providerId);
        return "redirect:/success";
    }

}
