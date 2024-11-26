package com.gj.service.iservice;

import com.gj.pojo.Temporary;
import com.gj.pojo.dto.TemporaryDto;

import java.util.List;

public interface ITemporaryService {
    Temporary add(TemporaryDto temporary);


    List<Temporary> getAll();

    void delete(Integer shopId);
}
