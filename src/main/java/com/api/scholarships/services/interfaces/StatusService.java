package com.api.scholarships.services.interfaces;

import com.api.scholarships.dtos.StatusResponse;
import com.api.scholarships.entities.Status;

public interface StatusService {
  public Status findId(Long id);
  public StatusResponse findAll(int page, int size, String sort, String order);
  public Status findByName(String statusName);
}
