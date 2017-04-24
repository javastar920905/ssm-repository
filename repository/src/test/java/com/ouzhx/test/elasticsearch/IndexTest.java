package com.ouzhx.test.elasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;

import com.ouzhx.repository.elasticsearch.BaseClient;
import com.ouzhx.repository.elasticsearch.TypeCreate;

/**
 * Created by ouzhx on 2017/4/24.
 */
public class IndexTest  {

  @Test // 创建索引
  public void createIndexTest() {
    TypeCreate typeCreate = new TypeCreate();
    IndexResponse response = BaseClient.client.prepareIndex(BaseClient.INDEX, BaseClient.TYPE, "1")
        .setSource(typeCreate.getContentBuilder()).get();
    System.out.println(response.toString());
    /**
     * IndexResponse[index=ozxtest_index,type=ozxtest_type,id=1,version=1,result=created,shards={"_shards":{"total":2,"successful":1,"failed":0}}]
     * 
     * _shards提供了索引操作过程中的复制过程信息。 total 指示对多少分片进行操作（主分片和备份分片）。 failed 操作失败的分片数和失败信息。 successful
     * 操作成功的分片数。
     */
  }
}
