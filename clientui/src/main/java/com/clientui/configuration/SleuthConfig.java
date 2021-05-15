package com.clientui.configuration;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Configuration;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@Configuration
public class SleuthConfig {
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
