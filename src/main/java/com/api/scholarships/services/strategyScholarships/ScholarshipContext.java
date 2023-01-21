package com.api.scholarships.services.strategyScholarships;

import com.api.scholarships.exceptions.BadRequestException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScholarshipContext implements InitializingBean {
  private List<ScholarshipStrategy> scholarshipStrategies;
  private Map<ScholarshipType, ScholarshipStrategy> map;

  public ScholarshipContext(List<ScholarshipStrategy> strategies ) {
    this.scholarshipStrategies=strategies;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.map= new HashMap<>();
    scholarshipStrategies.forEach(scholarshipStrategy -> map.put(scholarshipStrategy.typeStrategy(), scholarshipStrategy));
  }

  public ScholarshipStrategy getScholarshipStrategy(ScholarshipType type){
    if(!ScholarshipType.exists(type)){
      throw new BadRequestException("I");
    }
    return map.get(type);
  }
}
