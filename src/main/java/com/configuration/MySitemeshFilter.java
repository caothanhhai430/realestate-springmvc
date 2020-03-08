package com.configuration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySitemeshFilter extends ConfigurableSiteMeshFilter {
 
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/admin*", "/decorators/admin.jsp")
        .addExcludedPath("/api*");
      
    }
}
