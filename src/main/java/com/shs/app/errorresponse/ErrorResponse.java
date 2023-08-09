package com.shs.app.errorresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	
	private  Integer code ;
	private String msg ;
	private String date;
	private String status;
}
