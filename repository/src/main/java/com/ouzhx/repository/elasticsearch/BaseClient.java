package com.ouzhx.repository.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ouzhx on 2017/4/24.
 */
public class BaseClient {
  private static final String host = "192.168.1.180";
  private static final Integer port = 9300;
  // 测试索引名称
  public static final String INDEX = "ozxtest_index";
  public static final String TYPE = "ozxtest_type";
  public static  TransportClient client;
  static {
    try {
      client = new PreBuiltTransportClient(Settings.EMPTY)
          .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

  }

}
