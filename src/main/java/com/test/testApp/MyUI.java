package com.test.testApp;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;

import org.json.JSONException;

import com.test.testApp.weatherDataGetter;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	private VerticalLayout weatherLayout = new VerticalLayout();
	private VerticalLayout currencyLayout = new VerticalLayout();
	private VerticalLayout visitsLyout = new VerticalLayout();
	

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });

        
        //layout.setSizeFull();
        //layout.setSpacing(true);             
        
        
        layout.addComponents(name, button);
                
        layout.setComponentAlignment(name, Alignment.TOP_CENTER);
        layout.setComponentAlignment(button, Alignment.TOP_CENTER);
        //layout.setComponentAlignment(button, Alignment.);
       
       //constructWeatherLayout();
       
       //setContent(weatherLayout);
        constructUI();
        
    }
    
    void constructUI() {
    	HorizontalLayout mainLayout = new HorizontalLayout();
    	constructWeatherLayout();
    	constructCurrencyLayout();
    	constructVisitsLayout();
    	mainLayout.addComponents(weatherLayout,currencyLayout,visitsLyout);
    	setContent(mainLayout);
    	
    }
    
    void constructWeatherLayout() {
    	Label layoutTitle = new Label("Weather");
    	Label currentWeatherText = new Label("Now: ");
    	Label tommorowWeatherText = new Label("Tommorow: ");
    	Button updateBtn = new Button("Update");
    	
    	updateBtn.addClickListener(clickEvent -> {
    		Notification notify = new Notification("Updating Weather Data");
    		try {
				weatherDataGetter.doRequest();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		notify.setDelayMsec(1000);
    		notify.setPosition(Position.BOTTOM_RIGHT);
    		notify.show(Page.getCurrent());
    		
    		    		
    	});
    	   	
    	weatherLayout.addComponents(layoutTitle, currentWeatherText, tommorowWeatherText, updateBtn);    	
    }
    
    void constructCurrencyLayout() {
    	Label layoutTitle = new Label("Currency");
    	Label usdText = new Label("USD:");
    	Label eurText = new Label("EUR:");
    	Button updateBtn = new Button("Update");
    	
    	currencyLayout.addComponents(layoutTitle,usdText,eurText,updateBtn);
    	
    }
    
    void constructVisitsLayout() {
    	Label layoutTitle = new Label("Visits");
    	Label unicVisits = new Label("Unic Visits:");
    	Label totalVisits = new Label("Total Visits:");
    	
    	visitsLyout.addComponents(layoutTitle,unicVisits,totalVisits);
    }
    
    

    @WebServlet(urlPatterns = "/*", name = "MyTestApp", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
