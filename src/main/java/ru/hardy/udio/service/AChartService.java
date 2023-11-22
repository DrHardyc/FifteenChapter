package ru.hardy.udio.service;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.Locale;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.locale.Options;
import com.github.appreciated.apexcharts.config.plotoptions.bar.builder.ColorsBuilder;
import com.github.appreciated.apexcharts.config.plotoptions.bar.builder.RangesBuilder;
import com.github.appreciated.apexcharts.config.plotoptions.builder.BarBuilder;
import com.github.appreciated.apexcharts.helper.Series;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.dto.VolumeMedicalCareDTO;
import ru.hardy.udio.domain.chart.AChart;
import ru.hardy.udio.domain.chart.ApexChartToolbarLocale;
import ru.hardy.udio.domain.struct.People;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AChartService extends ApexChartsBuilder{
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

    public List<AChart> getFromFilePatient(List<People> dataFilePatientList, String param){
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

    public List<AChart> getFromVolumeMedicalCare(List<VolumeMedicalCareDTO> volumeMedicalCareDTOList) {
        List<AChart> aCharts = new ArrayList<>();
        volumeMedicalCareDTOList.forEach(volumeMedicalCareDTO ->
            aCharts.add(new AChart(String.valueOf(volumeMedicalCareDTO.getName()), volumeMedicalCareDTO.getDay4())));
        return aCharts;
    }


    public ApexCharts getTest() {
        return withChart(ChartBuilder.get()
                .withType(Type.BAR)
                .build())
                .withPlotOptions(PlotOptionsBuilder.get()
                        .withBar(BarBuilder.get()
                                .withHorizontal(true)
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false)
                        .build())
                .withSeries(new Series<>(400.0, 430.0, 448.0, 470.0, 540.0, 580.0, 690.0, 1100.0, 1200.0, 1380.0))
                .withXaxis(XAxisBuilder.get()
                        .withCategories()
                        .build()).build();
    }

    public ApexCharts getRangedVerticalBarChartExample(List<VolumeMedicalCareDTO> volumeMedicalCareDTOList) {
        List<Series<String>> seriesList = new ArrayList<>();


        volumeMedicalCareDTOList.forEach(volumeMedicalCareDTO -> {
            Series<String> series = new Series<>();
            series.setName(volumeMedicalCareDTO.getName());
            series.setData(new String[]{String.valueOf(volumeMedicalCareDTO.getDay1()), String.valueOf(volumeMedicalCareDTO.getDay2()),
                    String.valueOf(volumeMedicalCareDTO.getDay3()), String.valueOf(volumeMedicalCareDTO.getDay4()),
                    String.valueOf(volumeMedicalCareDTO.getDay5()), String.valueOf(volumeMedicalCareDTO.getDay6()),
                    String.valueOf(volumeMedicalCareDTO.getDay7())
            });
            seriesList.add(series);
        });

        return withChart(ChartBuilder.get()
                .withType(Type.BAR)
                .build())
                // .withColors() // Empty call makes chart to not render
                .withPlotOptions(PlotOptionsBuilder.get()
                        .withBar(BarBuilder.get()
                                .withHorizontal(false)
                                .withColumnWidth("55%")
                                .withColors(ColorsBuilder.get()
                                        // Ranges overwrite colors in range for all series
                                        .withRanges(RangesBuilder.get()
                                                .withFrom(50d)
                                                .withTo(75d)
                                                .withColor("#3d923d")
                                                .build(), RangesBuilder.get()
                                                .withFrom(75d)
                                                .withTo(100d)
                                                .withColor("#88593e")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false).build())
                .withStroke(StrokeBuilder.get()
                        .withShow(true)
                        .withWidth(2.0)
                        .withColors("transparent")
                        .build())
                .withSeries(seriesList.toArray(new Series[0]))
                .withXaxis(XAxisBuilder.get().withCategories("Вчера", "Позавчера", "3 дня назад", "4 дня назад", "5 дней назад", "6 дней назад", "7 дней назад").build())
                .withFill(FillBuilder.get()
                        .withOpacity(1.0).build()).build();
    }
}
