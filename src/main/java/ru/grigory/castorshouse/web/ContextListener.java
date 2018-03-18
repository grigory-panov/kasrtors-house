package ru.grigory.castorshouse.web;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 04.10.14
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();

        //sc.setAttribute("javax.servlet.jsp.jstl.fmt.fallbackLocale", "ru");
        //sc.setAttribute("javax.servlet.jsp.jstl.fmt.localizationContext", "messages.*");

        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("/META-INF/spring/application-context.xml");
        appContext.setServletContext(sc);
        new ContextLoader(appContext).initWebApplicationContext(sc);

        ServletRegistration.Dynamic springAppServlet = sc.addServlet("springapp", org.springframework.web.servlet.DispatcherServlet.class);
        springAppServlet.addMapping("*.html", "*.json", "/admin/action/*", "/image/*");
        springAppServlet.setLoadOnStartup(1);
        springAppServlet.setMultipartConfig(new MultipartConfigElement(System.getProperty("java.io.tmpdir")));

        FilterRegistration.Dynamic encodingFilter = sc.addFilter("CharacterEncodingFilter", org.springframework.web.filter.CharacterEncodingFilter.class);
        encodingFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), false, "springapp");
        encodingFilter.setInitParameter("encoding", "UTF-8");

        FilterRegistration.Dynamic securityFilter = sc.addFilter("springSecurityFilterChain", org.springframework.web.filter.DelegatingFilterProxy.class);
        securityFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
