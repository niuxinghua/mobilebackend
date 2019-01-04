package com.haier.repository;


import com.haier.model.AppInfo;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends CrudRepository<AppInfo, Integer> {
}
