package com.gj.service;

import com.gj.pojo.Temporary;
import com.gj.pojo.dto.TemporaryDto;
import com.gj.repository.TemporaryRepository;
import com.gj.service.iservice.ITemporaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemporaryService implements ITemporaryService {
    @Autowired
    TemporaryRepository temporaryRepository;

    @Override
    public Temporary add(TemporaryDto temporary) {
        Temporary t = new Temporary();
        BeanUtils.copyProperties(temporary, t);
        return temporaryRepository.save(t);
    }

    @Override
    public List<Temporary> getAll() {
        return (List<Temporary>) temporaryRepository.findAll();
    }

    @Override
    public void delete(Integer shopId) {
        temporaryRepository.deleteById(shopId);
    }
}
