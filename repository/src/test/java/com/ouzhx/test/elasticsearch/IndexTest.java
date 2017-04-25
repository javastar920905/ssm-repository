package com.ouzhx.test.elasticsearch;

import java.util.Map;

import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;

import com.ouzhx.repository.elasticsearch.BaseClient;
import com.ouzhx.repository.elasticsearch.TypeCreate;

/**
 * Created by ouzhx on 2017/4/24.
 */
public class IndexTest {

  // 测试索引名称
  public static final String INDEX = "ozxtest_index";
  public static final String TYPE_TABLE = "ozxtest_type";
  private TypeCreate typeCreate;
  private TransportClient client = BaseClient.client;
  private Map<String, Object> mapJson;
  private XContentBuilder contentBuilder;


  @Before
  public void instanceSource() {
    typeCreate = new TypeCreate();
    mapJson = typeCreate.getMapJson();
    contentBuilder = TypeCreate.getContentBuilder();
  }

  @Test // 创建索引
  public void createIndexTest() {
    IndexResponse response = client.prepareIndex(INDEX, TYPE_TABLE).setSource(mapJson).get();
    System.out.println(response.toString());
    /**
     * IndexResponse[index=ozxtest_index,TYPE_TABLE=ozxtest_TYPE_TABLE,id=1,version=1,result=created,shards={"_shards":{"total":2,"successful":1,"failed":0}}]
     * 
     * _shards提供了索引操作过程中的复制过程信息。 total 指示对多少分片进行操作（主分片和备份分片）。 failed 操作失败的分片数和失败信息。 successful
     * 操作成功的分片数。
     */
  }

  @Test
  public void updateDocument() {
    UpdateRequest updateRequest = new UpdateRequest();
    updateRequest.index(INDEX);
    updateRequest.type(TYPE_TABLE);
    updateRequest.id("1");
    updateRequest.doc(mapJson);
    client.update(updateRequest);
  }


  @Test
  public void getDocumentById() {
    GetResponse response = client.prepareGet(INDEX, TYPE_TABLE, "1").get();
    System.out.println(response.getSourceAsString());
  }

  @Test
  public void delDocumentById() {
    DeleteResponse response = client.prepareDelete(INDEX, TYPE_TABLE, "1").get();
    System.out.println(response.toString());
  }

  @Test // 更新插入
  public void update() {
    IndexRequest indexRequest = new IndexRequest(INDEX, TYPE_TABLE);
    indexRequest.source(mapJson);
    System.out.println(indexRequest.toString());

    // 更新插入 如果数据不存在，indexRequest将会被添加 //如果id=1存在 updateRequest将会执行更新
    UpdateRequest updateRequest =
        new UpdateRequest(INDEX, TYPE_TABLE, "1").doc(contentBuilder).upsert(indexRequest);
    client.update(updateRequest);
  }

  @Test // 多个索引 多个id查询
  public void multiGet() {
    MultiGetResponse multiGetResponses = client.prepareMultiGet().add(INDEX, TYPE_TABLE, "1").get();

    multiGetResponses
        .forEach(itemResonse -> System.out.println(itemResonse.getResponse().getSourceAsString()));
  }


  @Test // 批量操作api
  public void bulkRequest() {
    BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
    IndexRequest indexRequest = new IndexRequest(INDEX, TYPE_TABLE);
    indexRequest.source(mapJson);

    UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE_TABLE, "1");
    updateRequest.doc(mapJson);
    bulkRequestBuilder.add(indexRequest);
    bulkRequestBuilder.add(client.prepareIndex(INDEX, TYPE_TABLE).setSource(contentBuilder));
    bulkRequestBuilder.add(updateRequest);

    BulkResponse responses = bulkRequestBuilder.get();
    if (responses.hasFailures()) {
      System.out.println(responses.getItems().toString());
    }
  }

  @Test //
  public void prepareQuery() {
    QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
        .add(QueryBuilders.termQuery("user", "ouzhx")).add(QueryBuilders.prefixQuery("no", "01"));
    System.out.println(queryBuilder.toString());
    SearchResponse response =
        client.prepareSearch(INDEX).setQuery(queryBuilder).execute().actionGet();
    System.out.println(response.toString());
  }

  @Test
  public void matchAll() {
    // QueryBuilders 设置查询条件
    QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

    // 执行查询
    SearchResponse response =
        client.prepareSearch(INDEX).setQuery(queryBuilder).execute().actionGet();
    // 打印查询结果
    System.out.println(response.toString());
  }

  @Test // 匹配查询
  public void matchQuery() {

    QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "ouzhx");
  }

  @Test // 分页查询 排序
  public void pageQuery() {
    SearchResponse searchResponse = client.prepareSearch(INDEX)
        .setQuery(QueryBuilders.matchQuery("name", "ouzhx")).addSort(SortBuilders.fieldSort("name"))
        .addSort("_score", SortOrder.DESC).setFrom(1).setSize(2).execute().actionGet();
    System.out.println(searchResponse.toString());

  }

  @Test // MultiSearch
  public void multiSearch() {
    client.prepareMultiSearch().add(client.prepareSearch(INDEX).setTypes(TYPE_TABLE).request())
        .add(client.prepareSearch(INDEX)).execute().actionGet();

    // 在user 和name字段中 查询 ouzhx这个字符串
    QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("ouzhx", "user", "name");
    SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE_TABLE)
        .setQuery(queryBuilder).execute().actionGet();
    System.out.println(response.toString());

  }

  @Test //bool 查询
  public void boolQuery() throws Exception {
    QueryBuilder queryBuilder =
        QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", "ouzhx"))
            .mustNot(QueryBuilders.termQuery("name", "ouzhx 2"))
            .must(QueryBuilders.termQuery("user", "ouzhx"))
            .should(QueryBuilders.termQuery("name", ""));
    SearchResponse searchResponse = client.prepareSearch(INDEX).setTypes(TYPE_TABLE)
        .setQuery(queryBuilder).execute().actionGet();
    System.out.println(searchResponse.toString());
  }

  @Test
  public void adminApi() {
    ClusterHealthResponse response =
        client.admin().cluster().prepareHealth(INDEX).execute().actionGet();
    System.out.println(response.toString());
  }
}
