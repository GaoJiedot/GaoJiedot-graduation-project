package com.gj.service;

import com.gj.pojo.Tabulate;
import com.gj.pojo.dto.TabulateDto;
import com.gj.repository.TabulateRepository;
import com.gj.service.iservice.ITabulateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabulateService implements ITabulateService {
    @Autowired
    TabulateRepository tabulateRepository;

    @Override
    public Tabulate add(TabulateDto tabulate) {
        Tabulate tabulatePojo = new Tabulate();
        BeanUtils.copyProperties(tabulate, tabulatePojo);
        return tabulateRepository.save(tabulatePojo);
    }

    @Override
    public Tabulate get(Integer tabulateId) {
        return tabulateRepository.findById(tabulateId).orElseThrow(() -> new IllegalArgumentException("List not found"));
    }

    @Override
    public List<Tabulate> getByType(Integer tabulateType) {
        return tabulateRepository.findByTabulateType(tabulateType);
    }

    @Override
    public Tabulate update(TabulateDto tabulate) {
        Tabulate tabulatePojo = new Tabulate();
        BeanUtils.copyProperties(tabulate, tabulatePojo);
        return tabulateRepository.save(tabulatePojo);
    }

    @Override
    public void delete(Integer tabulateId) {
        tabulateRepository.deleteById(tabulateId);
    }

    @Override
    public List<Tabulate> getByTabulateName(String tabulateName) {
        return tabulateRepository.findByTabulateName(tabulateName);
    }

}