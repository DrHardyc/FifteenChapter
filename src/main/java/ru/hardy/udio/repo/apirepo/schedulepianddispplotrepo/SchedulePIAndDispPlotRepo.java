package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlot;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.List;

@Repository
public interface SchedulePIAndDispPlotRepo extends JpaRepository<SchedulePIAndDispPlot, Long> {
    @Query("select t from SchedulePIAndDispPlot t join t.requestRecord rr join rr.request r where r.medicalOrganization.codeMO = :codeMO" +
            " and rr.codeDep = :codeDep")
    SchedulePIAndDispPlot findByCodeMOAndRequestRecord_CodeDep(int codeMO, int codeDep);

    @Query("select t from SchedulePIAndDispPlot t join t.requestRecord rr join rr.request r where r.medicalOrganization.codeMO = :codeMO")
    List<SchedulePIAndDispPlot> findAllByCodeMO(int codeMO);
}
