package com.kalsym.flowbuilder.formatted.models.vertex;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WizardContent {

    private String activePage;
    private String store ;
    private List<WizardResource> resources;
    private List<WizardPage> pages;
}
