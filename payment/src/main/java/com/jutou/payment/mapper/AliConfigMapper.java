package com.jutou.payment.mapper;

import org.springframework.stereotype.Repository;

import com.jutou.payment.model.AliConfig;

@Repository
public interface AliConfigMapper {

	 AliConfig get(Integer configId);
}
