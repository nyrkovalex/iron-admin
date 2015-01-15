package com.github.nyrkovalex.ironadmin.core.pages;

import java.util.Optional;

public class PageRequest {
    private final String pageUrl;
    private final Optional<String> entityId;

    public PageRequest(String pageUrl, Optional<String> entityId) {
        this.pageUrl = pageUrl;
        this.entityId = entityId;
    }

    public String pageUrl() {
        return pageUrl;
    }

    public Optional<String> entityId() {
        return entityId;
    }
}
