package com.azad.luminor.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {

    private final Page page;

    private final Locator acceptCookiesButton;
    private final Locator cookiePolicy;
    private final Locator siteMenu;
    private final Locator aboutUs;

    public HomePage(Page page) {
        this.page = page;

        acceptCookiesButton = page.locator("#onetrust-accept-btn-handler");

        cookiePolicy = page.locator("#onetrust-policy");

        siteMenu = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Site menu"));

        aboutUs = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("About Us"));
    }

    public void acceptCookiesIfPresent() {
        if (acceptCookiesButton.isVisible()) {
            acceptCookiesButton.click();assertThat(cookiePolicy).not().isVisible();
        }
    }

    public void openSiteMenu() {

        siteMenu.click();
    }

    public void openAboutUs() {
        aboutUs.click();

        assertThat(aboutUs).hasAttribute("aria-expanded", "true");
    }

    public int getAboutUsItemCount() {
        String submenuId = aboutUs.getAttribute("data-meta-sub-menu");

        return page.locator("#" + submenuId).getByRole(AriaRole.LISTITEM).count();
    }

    public void openFinancialReports() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Financial Reports")).click();
    }
}