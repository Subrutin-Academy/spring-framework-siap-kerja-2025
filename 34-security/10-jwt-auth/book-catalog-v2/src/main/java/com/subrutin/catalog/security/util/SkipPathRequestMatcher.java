package com.subrutin.catalog.security.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;

public class SkipPathRequestMatcher implements RequestMatcher {
	
	private OrRequestMatcher skipMacther;
	
	private OrRequestMatcher processingMatcher;
	
	public SkipPathRequestMatcher(List<String> pathToSkip, 
			List<String> processingPath) {
		List<RequestMatcher> skip = pathToSkip.stream().map(s->new AntPathRequestMatcher(s))
			.collect(Collectors.toList());
		skipMacther = new OrRequestMatcher(skip);
		
		List<RequestMatcher> processing = processingPath.stream().map(p->new AntPathRequestMatcher(p))
				.collect(Collectors.toList());
		processingMatcher = new OrRequestMatcher(processing);		
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		if(skipMacther.matches(request)) {
			return false;
		}
		return processingMatcher.matches(request);
	}

	
	

}
