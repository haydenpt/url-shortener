package org.interstellar.urlshortener.factory;

import org.interstellar.urlshortener.impl.DefaultStrategy;
import org.interstellar.urlshortener.impl.PersonalizeStrategy;
import org.interstellar.urlshortener.impl.UrlShortenerStrategy;
import org.springframework.stereotype.Component;

@Component
public class URLShortenerStrategyFactory {
    public UrlShortenerStrategy createStrategy(String strategy) {
        if ("personalize".equals(strategy)) {
            return new PersonalizeStrategy();
        }
        return new DefaultStrategy();
    }
}
