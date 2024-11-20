package com.gj.service.iservice;

import com.gj.pojo.Tabulate;
import com.gj.pojo.dto.TabulateDto;

import java.util.List;

public interface ITabulateService {

    Tabulate add(TabulateDto tabulate);

    Tabulate get(Integer tabulateId);

    List<Tabulate> getByType(Integer tabulateType);

    Tabulate update(TabulateDto tabulate);

    void delete(Integer tabulateId);

    List<Tabulate> getByTabulateName(String tabulateName);
}
