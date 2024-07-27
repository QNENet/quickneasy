package com.qnenet.views.welcome;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Welcome")
@Route(value = "")
@RouteAlias(value = "")
public class WelcomeView extends VerticalLayout {

    public WelcomeView() {

        setSizeFull();
        setSpacing(false);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        H1 header = new H1("QuickNEasy");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new H3("Software that enables the QNE Community"));

    }

}
