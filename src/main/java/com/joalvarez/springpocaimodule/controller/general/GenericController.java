package com.joalvarez.springpocaimodule.controller.general;

import com.joalvarez.springpocaimodule.service.general.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface GenericController<K, O, S extends GenericService<O, K>> {

	List<O> getAll();
	O get(K id);
	O save(O entity);
	Page<O> getPaginatedWithFilters(int page, int size, String sortBy, Sort.Direction sortDir, Map<String, String> filters);
}