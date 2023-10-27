package ru.hardy.udio.repo.apirepo.operatingschedulerepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;

import java.util.Collection;

@Repository
public interface OperatingScheduleRequestRepo extends JpaRepository<OperatingScheduleRequest, Long> {

    //Collection<Object> findAllByReqIDAnd(String reqID, int codeMO);
}
