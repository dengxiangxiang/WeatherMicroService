package com.dxx.springcloud.weather.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {
//	@Autowired
//	private RestTemplateBuilder builder;
//	
//	@Bean
//	public RestTemplate restTemplate() {
//		return builder.build();
//	}
	
	/**
	 * 返回RestTemplate
	 * @param factory ClientHttpRequestFactory
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory){
		RestTemplate restTemplate = new RestTemplate(factory);
		ChangeStringEncodeType(restTemplate,StandardCharsets.UTF_8);
		return restTemplate;
	}
	
	private void ChangeStringEncodeType(RestTemplate restTemplate,Charset charset) {
		// TODO Auto-generated method stub
		if (null == restTemplate || ObjectUtils.isEmpty(restTemplate.getMessageConverters())) {
			return;
		}
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		for (int i = 0; i < messageConverters.size(); i++) {
			HttpMessageConverter<?> httpMessageConverter = messageConverters.get(i);
			if (httpMessageConverter.getClass().equals(StringHttpMessageConverter.class)) {
				messageConverters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
				}
			}
	}

	/**
	 * ClientHttpRequestFactory接口的另一种实现方式（推荐使用），即：
	 * HttpComponentsClientHttpRequestFactory：底层使用Httpclient连接池的方式创建Http连接请求
	 * @return HttpComponentsClientHttpRequestFactory
	 */
	@Bean
	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
		//Httpclient连接池，长连接保持30秒
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
		
		//设置总连接数
		connectionManager.setMaxTotal(5);
		//设置同路由的并发数
		connectionManager.setDefaultMaxPerRoute(5);
		
		//设置header
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.04"));
		headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3"));
		headers.add(new BasicHeader("Connection", "keep-alive"));
		
		//创建HttpClient
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultHeaders(headers)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)) //设置重试次数
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()) //设置保持长连接
                .build();
		
        //创建HttpComponentsClientHttpRequestFactory实例
        HttpComponentsClientHttpRequestFactory requestFactory = 
				new HttpComponentsClientHttpRequestFactory(httpClient);
		
        //设置客户端和服务端建立连接的超时时间
        requestFactory.setConnectTimeout(5000);
		//设置客户端从服务端读取数据的超时时间
        requestFactory.setReadTimeout(5000);
        //设置从连接池获取连接的超时时间，不宜过长
        requestFactory.setConnectionRequestTimeout(200);
        //缓冲请求数据，默认为true。通过POST或者PUT大量发送数据时，建议将此更改为false，以免耗尽内存
        requestFactory.setBufferRequestBody(false);
        
        return requestFactory;
	}
}
