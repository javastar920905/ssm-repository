package com.ouzhx.web.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/15.
 */
@RestController
@RequestMapping("test")
public class TestController {

  @RequestMapping(value = "/",method = RequestMethod.GET)
  public String test() {
    return "test ";
  }
}
