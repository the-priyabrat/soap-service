package com.interland.ipsh.soaps.config;

import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SoapWsConfig extends WsConfigurerAdapter {

	@Bean
	ServletRegistrationBean<MessageDispatcherServlet> messageSDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}

	@Bean(name = "getDesignationList")
	DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("DesignationList");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace("http://www.example.org/designationList");
		defaultWsdl11Definition.setSchema(schema);
		return defaultWsdl11Definition;
	}

	@Bean
	XsdSchema schema() {
		return new SimpleXsdSchema(new ClassPathResource("designationList.xsd"));
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(payloadValidatingInterceptor());
	}

	@Bean
	PayloadValidatingInterceptor payloadValidatingInterceptor() {
		PayloadValidatingInterceptor interceptor = new PayloadValidatingInterceptor();
		interceptor.setValidateRequest(true);
		interceptor.setValidateResponse(true);
		interceptor.setXsdSchema(schema());
		return interceptor;
	}

}
