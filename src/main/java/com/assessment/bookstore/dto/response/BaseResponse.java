package com.assessment.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse{

	private String responseCode;
	private String responseMessage;
}
