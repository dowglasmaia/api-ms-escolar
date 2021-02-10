package com.maia.mspagamento.exceptions;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private String message;
	private String details;

}
