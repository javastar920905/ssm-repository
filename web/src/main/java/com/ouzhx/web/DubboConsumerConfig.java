package com.ouzhx.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by ouzhx on 2017/4/26.
 */
@Configuration
@ImportResource("classpath:dubbo_client.xml")
public class DubboConsumerConfig {

}
