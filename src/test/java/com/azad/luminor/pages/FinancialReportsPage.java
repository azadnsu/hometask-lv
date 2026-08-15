package com.azad.luminor.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FinancialReportsPage {

    private final Locator year2026;
    private final Locator q2Report;

    public FinancialReportsPage(Page page) {

        year2026 = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("2026"));

        q2Report = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Luminor Bank AS, Interim report Q2 2026"));
    }

    public void verify2026SectionIsOpen() {
        assertThat(year2026).hasAttribute("aria-expanded", "true");
    }

    public void verifyQ2ReportIsPresent() {
        assertThat(q2Report).isVisible();

        assertThat(q2Report)
                .hasAttribute("href", "/sites/default/files/docs/finansu_parskati/luminor_bank_interim_report_2q26.pdf");
    }
}