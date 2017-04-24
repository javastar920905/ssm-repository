package com.ouzhx.test.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.junit.Before;
import org.junit.Test;

import com.ouzhx.repository.elasticsearch.BaseClient;
import com.ouzhx.repository.elasticsearch.TypeCreate;

import java.util.Map;

/**
 * Created by ouzhx on 2017/4/24.
 */
public class IndexTest {

  private TypeCreate typeCreate;
  private Map<String, Object> mapJson;

  @Before
  public void instanceSource() {
    typeCreate = new TypeCreate();
  }

  @Test // 创建索引
  public void createIndexTest() {
    IndexResponse response = BaseClient.client.prepareIndex(BaseClient.INDEX, BaseClient.TYPE)
        .setSource(typeCreate.getMapJson()).get();
    System.out.println(response.toString());
    /**
     * IndexResponse[index=ozxtest_index,type=ozxtest_type,id=1,version=1,result=created,shards={"_shards":{"total":2,"successful":1,"failed":0}}]
     * 
     * _shards提供了索引操作过程中的复制过程信息。 total 指示对多少分片进行操作（主分片和备份分片）。 failed 操作失败的分片数和失败信息。 successful
     * 操作成功的分片数。
     */
  }


  @Test
  public void getDocumentById() {
    GetResponse response =
        BaseClient.client.prepareGet(BaseClient.INDEX, BaseClient.TYPE, "1").get();
    System.out.println(response.getSourceAsString());
  }

  @Test
  public void delDocumentById() {
    DeleteResponse response =
        BaseClient.client.prepareDelete(BaseClient.INDEX, BaseClient.TYPE, "1").get();
    System.out.println(response.toString());
  }

  @Test // 更新插入
  public void update() {
    IndexRequest indexRequest = new IndexRequest(BaseClient.INDEX, BaseClient.TYPE);
    indexRequest.source(typeCreate.getMapJson());
    System.out.println(indexRequest.toString());

    // 更新插入 如果数据不存在，indexRequest将会被添加 //如果id=1存在 updateRequest将会执行更新
    UpdateRequest updateRequest = new UpdateRequest(BaseClient.INDEX, BaseClient.TYPE, "1")
        .doc(TypeCreate.getContentBuilder()).upsert(indexRequest);
    BaseClient.client.update(updateRequest);
  }


}
