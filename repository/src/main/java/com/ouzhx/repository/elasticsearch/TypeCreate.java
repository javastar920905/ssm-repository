package com.ouzhx.repository.elasticsearch;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;

import com.ouzhx.common.ReflectMatch;

/**
 * Created by ouzhx on 2017/4/24. 创建索引 创建索引API允许调用者将一个Json类型的数据存入到一个特定的索引下，并使其可以被检索到
 * 
 * 构建Json数据 这里有几种构建Json数据的的方法。 手动构建Json类型的字节数组（byte[]）或字符串。 使用将自动转换为Json的Map。 使用第三方类库将你的实体序列化成Json。
 * 使用内部工具类XContentFactory.jsonBuilder()。
 */
public class TypeCreate extends BaseClient {
  /**
   * 使用map 构建json
   * 
   * @return
   */
  public Map<String, Object> getMapJson() {
    Map<String, Object> json = new HashMap<>();
    ReflectMatch.setValue(json);
    return json;
  }

  /**
   * ElasticSearch提供一个工具类来构建Json内容。 你可以使用startArray（String） 和endArray（）方法添加数组。
   * 顺便提醒一下，field方法支持多种对象，你可以添加数字，日期，甚至其他的XContentBuilder对象。
   * 
   * @return
   * @throws Exception
   */
  public XContentBuilder getContentBuilder() {
    XContentBuilder contentBuilder = null;
    try {
      contentBuilder = jsonBuilder().startObject().field("user", "kimchy")
          .field("postDate", new Date()).field("message", "trying out Elasticsearch").endObject();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contentBuilder;
  }



}
