package com.kimreporter.utils;

import java.util.List;

import com.kimreporter.domain.AdaptationVO;

public class ContainsName {
	
	public boolean containsName(final List<AdaptationVO> list, final String new_id){
	    return list.stream().filter(o -> o.getAdaptation_id().equals(new_id)).findFirst().isPresent();
	}

}
