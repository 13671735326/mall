package com.mall.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class GetUserParam {

	@Max(100)
	@Min(1)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
