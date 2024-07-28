package com.qnenet.views.welcome;

import com.qnenet.constants.QRoute;
import com.qnenet.system.QSystem;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@AnonymousAllowed
@PageTitle("Welcome")
@Route(value = QRoute.WELCOME)
public class WelcomeView extends VerticalLayout {

    private QSystem system;

    public WelcomeView(QSystem system) {
        this.system = system;
        if (system.isNew()) {
            setupNewSystem();
        } else {
            setupExistingSystem();
        }
        setSizeFull();
        setSpacing(false);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");


    }

    private void setupExistingSystem() {
        H1 header = new H1("QuickNEasy");
        // header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        H3 subHeader = new H3("Software that enables the QNE Community");
        H2 note = new H2("System Restart");
        note.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header, subHeader, note);


    }

    private void setupNewSystem() {
        H1 header = new H1("QuickNEasy");
        // header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        H3 subHeader = new H3("Software that enables the QNE Community");
        H2 note = new H2("New Installation");
        note.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header, subHeader, note);
        system.setNew(false);
    }

}
