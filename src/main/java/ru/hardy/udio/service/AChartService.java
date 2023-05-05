package ru.hardy.udio.service;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.Locale;
import com.github.appreciated.apexcharts.config.builder.ChartBuilder;
import com.github.appreciated.apexcharts.config.builder.DataLabelsBuilder;
import com.github.appreciated.apexcharts.config.builder.PlotOptionsBuilder;
import com.github.appreciated.apexcharts.config.builder.XAxisBuilder;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.toolbar.Csv;
import com.github.appreciated.apexcharts.config.chart.toolbar.Export;
import com.github.appreciated.apexcharts.config.locale.Options;
import com.github.appreciated.apexcharts.config.locale.Toolbar;
import com.github.appreciated.apexcharts.config.plotoptions.bar.Colors;
import com.github.appreciated.apexcharts.config.plotoptions.builder.BarBuilder;
import com.github.appreciated.apexcharts.helper.Series;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.chart.AChart;
import ru.hardy.udio.domain.chart.ApexChartToolbarLocale;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AChartService {
    public List<AChart> getFromPeople(List<People> peopleList, String param){
        List<AChart> aCharts = new ArrayList<>();
        switch (param){
            case "age" -> {
                peopleList.forEach(people -> {
                    aCharts.add(new AChart(people.getFIO(), people.getAge()));
                });

            }
            case "kolsluch" -> {

            }
        }
        return aCharts;
    }

    public List<AChart> getFromFilePatient(List<DataFilePatient> dataFilePatientList, String param){
        List<AChart> aCharts = new ArrayList<>();
        switch (param){
            case "age" -> {
                dataFilePatientList.forEach(dataFilePatient -> {
                    aCharts.add(new AChart(dataFilePatient.getFIO(), dataFilePatient.getAge()));
                });

            }
            case "kolsluch" -> {

            }
        }
        return aCharts;
    }


    public ApexCharts getAC(List<AChart> aChart) {

        Locale locale = new Locale();
        locale.setName("ru");
        Options options = new Options();
        ApexChartToolbarLocale toolbarLocale = new ApexChartToolbarLocale();
        toolbarLocale.setExportToPNG("Скачать PNG");
        toolbarLocale.setExportToSVG("Скачать SVG");
        toolbarLocale.setExportToCSV("Скачать CSV");
        options.setToolbar(toolbarLocale);
        locale.setOptions(options);

        ApexCharts chart = ApexChartsBuilder.get().withChart(ChartBuilder.get()
                        .withType(Type.BAR)
                        .withLocales(locale)
                        .withDefaultLocale("ru")
                        .build())
                .withPlotOptions(PlotOptionsBuilder.get()
                        .withBar(BarBuilder.get()
                                .withHorizontal(true)
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false)
                        .build())
                .withSeries(new Series<>("", aChart.stream().map(AChart::getValue).toArray()))
                .withXaxis(XAxisBuilder.get()
                        .withCategories(aChart.stream().map(AChart::getName).collect(Collectors.toList()))
                        .build())
                .build();
        chart.setHeight("400px");
        chart.setWidth("700px");

        return chart;
    }


}
