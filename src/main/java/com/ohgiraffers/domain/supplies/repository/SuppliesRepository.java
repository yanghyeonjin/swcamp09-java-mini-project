package com.ohgiraffers.domain.supplies.repository;

import com.ohgiraffers.domain.supplies.aggregate.Supplies;

import java.util.ArrayList;

public class SuppliesRepository {

    private ArrayList<Supplies> allSupplies = new ArrayList<>();

    public ArrayList<Supplies> selectAllSupplies() {
        return allSupplies;
    }
}
