package org.rubik.sandbox.archaius;

import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.google.common.collect.Lists;
import com.netflix.config.AbstractPollingScheduler;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.PolledConfigurationSource;

/**
 * Archaius依赖： archaius-core, guava, jsr305, jackson-mapper-asl, slf4j-api,
 * commons-configuration, commons-lang, commons-logging, annotations
 * 
 * @author xiajinxin
 * 
 */
public class ArchaiusApp {
	private static final Logger logger = LoggerFactory.getLogger(ArchaiusApp.class);

	public static void main(String... args) throws IOException, ConfigurationException {
//		ConfigurationManager.loadPropertiesFromResources("sample-client.properties");  // 1
//        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));

        // configuration from local properties file
        String fileName = "sample-client.properties";
        ConcurrentMapConfiguration configFromPropertiesFile = new ConcurrentMapConfiguration(new PropertiesConfiguration(fileName));
        // configuration from system properties
        ConcurrentMapConfiguration configFromSystemProperties = new ConcurrentMapConfiguration(new SystemConfiguration());
        // configuration from a dynamic source
//        PolledConfigurationSource source = createMyOwnSource();
//        AbstractPollingScheduler scheduler = createMyOwnScheduler();
//        DynamicConfiguration dynamicConfiguration = new DynamicConfiguration(source, scheduler);
        
        // create a hierarchy of configuration that makes
        // 1) dynamic configuration source override system properties and,
        // 2) system properties override properties file
        ConcurrentCompositeConfiguration finalConfig = new ConcurrentCompositeConfiguration();
//        finalConfig.add(dynamicConfiguration, "dynamicConfig");
        finalConfig.addConfiguration(configFromSystemProperties, "systemConfig");
        finalConfig.addConfiguration(configFromPropertiesFile, "fileConfig");

        // install with ConfigurationManager so that finalConfig
        // becomes the source of dynamic properties
        ConfigurationManager.install(finalConfig);

//        List<?> properties = Lists.newArrayList(ConfigurationManager.getConfigInstance().getKeys());
//        for (Object prop : properties) {
//			logger.debug("{}[{}]", prop, ConfigurationManager.getConfigInstance().getProperty(prop.toString()));
//		}
//        System.out.println(ConfigurationManager.getConfigInstance().getKeys());
//        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));
//        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.OkToRetryOnAllOperations"));



//        RestClient client = (RestClient) ClientFactory.getNamedClient("sample-client");  // 2
//        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build(); // 3
//        for (int i = 0; i < 20; i++)  {
//        	HttpResponse response = client.executeWithLoadBalancer(request); // 4
//        	System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
//        }
//        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
//        System.out.println(lb.getLoadBalancerStats());
//        ConfigurationManager.getConfigInstance().setProperty(
//        		"sample-client.ribbon.listOfServers", "www.linkedin.com:80,www.google.com:80"); // 5
//        System.out.println("changing servers ...");
//        Thread.sleep(3000); // 6
//        for (int i = 0; i < 20; i++)  {
//        	HttpResponse response = client.executeWithLoadBalancer(request);
//        	System.out.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
//        }
//        System.out.println(lb.getLoadBalancerStats()); // 7
	}
}
