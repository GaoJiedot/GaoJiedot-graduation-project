package com.gj.service.iservice;

import com.gj.pojo.Tabulate;
import com.gj.pojo.dto.TabulateDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITabulateService {

    Tabulate add(TabulateDto tabulate);

    Tabulate get(Integer tabulateId);



    Tabulate update(TabulateDto tabulate);

    void delete(Integer tabulateId);

    List<Tabulate> getByTabulateName(String tabulateName);

    List<Tabulate> getByShopId(Integer shopId);

    Tabulate uploadTabulateImages(Integer tabulateId, String avatarPath);

    Page<Tabulate> getByTypeWithPagination(Integer tabulateType, int page, int pageSize);
}
