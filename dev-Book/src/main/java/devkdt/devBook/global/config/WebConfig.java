package devkdt.devBook.global.config;

import devkdt.devBook.global.filter.AuthorizationFilter;
import devkdt.devBook.global.filter.LoginFilter;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public ServletContextInitializer sessionCookieConfig() {
    return new ServletContextInitializer() {
      @Override
      public void onStartup(ServletContext servletContext) throws ServletException {
        //servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
        SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(false);
      }
    };
  }

  @Bean
  public FilterRegistrationBean loginFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

    filterRegistrationBean.setFilter(new LoginFilter());
    filterRegistrationBean.setOrder(1);
    filterRegistrationBean.addUrlPatterns("/*");
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean authorizationFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();

    filterRegistrationBean.setFilter(new AuthorizationFilter());
    filterRegistrationBean.setOrder(2);
    filterRegistrationBean.addUrlPatterns("/*");
    return filterRegistrationBean;
  }
}
