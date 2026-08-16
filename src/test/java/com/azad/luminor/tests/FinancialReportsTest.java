package com.azad.luminor.tests;

import com.azad.luminor.config.LuminorConfig;
import com.azad.luminor.pages.FinancialReportsPage;
import com.azad.luminor.pages.HomePage;
import com.microsoft.playwright.*;

import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FinancialReportsTest {

    static Playwright playwright;
    static Browser browser;
    private static BrowserContext context;
    private static Page page;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true)
        );

        context = browser.newContext();

        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        page = context.newPage();
    }

    @AfterAll
    static void tearDown() {
        context.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get(
                                "build/playwright-traces/"
                                        + UUID.randomUUID()
                                        + ".zip"
                        ))
        );

        context.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Verify 2026 financial report is available")
    void shouldDisplay2026FinancialReport() {

        HomePage homePage = new HomePage(page);
        FinancialReportsPage financialReportsPage = new FinancialReportsPage(page);

        // Open Luminor
        page.navigate(LuminorConfig.BASE_URL);

        assertThat(page).hasURL(LuminorConfig.BASE_URL);
        assertThat(page).hasTitle(Pattern.compile("Luminor"));

        // Accept cookies
        homePage.acceptCookiesIfPresent();

        // Open Site menu
        homePage.openSiteMenu();

        // Open About Us
        homePage.openAboutUs();

        // Verify About Us contains 11 items
        assertEquals(11, homePage.getAboutUsItemCount());

        // Open Financial Reports
        homePage.openFinancialReports();

        // Verify 2026 section
        financialReportsPage.verify2026SectionIsOpen();

        // Verify report
        financialReportsPage.verifyQ2ReportIsPresent();
    }
}