package com.quanlybanhang.selling.dao;

import java.util.List;
public interface GenericDAO<T> { 
    List<T> findAll();
}
