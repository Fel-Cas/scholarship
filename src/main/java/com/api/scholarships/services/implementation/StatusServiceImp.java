package com.api.scholarships.services.implementation;

import com.api.scholarships.dtos.StatusResponse;
import com.api.scholarships.entities.Status;
import com.api.scholarships.mappers.StatusMapper;
import com.api.scholarships.repositories.StatusRepository;
import com.api.scholarships.services.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StatusServiceImp implements StatusService {

  @Autowired
  private StatusRepository statusRepository;
  @Autowired
  private StatusMapper statusMapper;

  @Override
  public Status findId(Long id) {
    return null;
  }

  @Override
  public StatusResponse findAll(int page, int size, String sort, String order) {
    return null;
  }

  @Override
  public Status findByName(String statusName) {
    return null;
  }
}
