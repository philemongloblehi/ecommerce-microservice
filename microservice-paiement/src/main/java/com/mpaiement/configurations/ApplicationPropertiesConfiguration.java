package com.mpaiement.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@Component
@ConfigurationProperties("mes-config")
@RefreshScope
public class ApplicationPropertiesConfiguration {
}
