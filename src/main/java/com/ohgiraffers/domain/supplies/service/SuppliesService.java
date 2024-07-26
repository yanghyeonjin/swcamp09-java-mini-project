package com.ohgiraffers.domain.supplies.service;

import com.ohgiraffers.domain.supplies.aggregate.Supplies;
import com.ohgiraffers.domain.supplies.repository.SuppliesRepository;

import java.util.ArrayList;

public class SuppliesService {

    private final SuppliesRepository suppliesRepository = new SuppliesRepository();

    public ArrayList<Supplies> findAllSupplies() {
        return suppliesRepository.selectAllSupplies();
    }
}
