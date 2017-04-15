package com.ouzhx.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/4/15.
 */
@RestController
@RequestMapping("index")
public class IndexController {
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView pageIndex() {
    ModelAndView mv = new ModelAndView("/webapp/jsp/index.jsp");
    return mv;
  }

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    return "hello"+"hot33";
  }
}
