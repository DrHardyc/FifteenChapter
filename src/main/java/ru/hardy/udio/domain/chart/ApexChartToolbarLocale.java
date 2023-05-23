package ru.hardy.udio.domain.chart;

import com.github.appreciated.apexcharts.config.locale.Toolbar;

public class ApexChartToolbarLocale extends Toolbar {
    private String exportToCSV;

    public String getExportToCSV() {
        return exportToCSV;
    }

    public void setExportToCSV(String exportToCSV) {
        this.exportToCSV = exportToCSV;
    }
}
