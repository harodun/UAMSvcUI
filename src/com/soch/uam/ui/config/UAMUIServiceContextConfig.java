/**
 * 
 */
package com.soch.uam.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author sysadmin
 *
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.soch.uam")
@PropertySource(value = { "file:c:\\harish\\application.properties"})
public class UAMUIServiceContextConfig {
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	  messageBundle.setBasename("file:c:\\harish\\messages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	}

}
