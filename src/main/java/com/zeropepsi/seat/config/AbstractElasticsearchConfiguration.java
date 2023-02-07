package com.zeropepsi.seat.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

/**
 * 이 클래스에서 ElasticsearchOperations을 Bean으로 등록하고 있음
 * 구현체는 ElasticsearchRestTemplate.
 * 해당 부분을 개발자가 커스텀하게 Bean 등록을 해 주면 된다.
 */
public abstract class AbstractElasticsearchConfiguration extends ElasticsearchConfigurationSupport {

	@Bean
	public abstract RestHighLevelClient elasticsearchClient();

	/**
	 * ElasticsearchOperations interface
	 * - DocumentOperations : id를 기반으로 entity를 저장, 업데이트, 조회
	 * - SearchOperations : query를 사용하여 여러 entity를 검색
	 */
	@Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
	public ElasticsearchOperations elasticsearchOperations(
		ElasticsearchConverter elasticsearchConverter,
		RestHighLevelClient elasticsearchClient) {

		ElasticsearchRestTemplate template = new ElasticsearchRestTemplate(elasticsearchClient, elasticsearchConverter);
		template.setRefreshPolicy(refreshPolicy());

		return template;
	}

}
