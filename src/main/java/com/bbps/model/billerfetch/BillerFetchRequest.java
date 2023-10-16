package com.bbps.model.billerfetch;

import java.io.Serializable;

import lombok.Data;

@Data
public class BillerFetchRequest implements Serializable {

	private static final long serialVersionUID = -7914477156628847490L;
	public String billerId;
	public String billerCategoryName;
	public String searchMyBiller;

}
