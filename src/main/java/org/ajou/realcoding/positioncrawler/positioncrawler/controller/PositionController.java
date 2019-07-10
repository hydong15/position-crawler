package org.ajou.realcoding.positioncrawler.positioncrawler.controller;

import org.ajou.realcoding.positioncrawler.positioncrawler.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {
    @Autowired
    PositionService positionService;
}
