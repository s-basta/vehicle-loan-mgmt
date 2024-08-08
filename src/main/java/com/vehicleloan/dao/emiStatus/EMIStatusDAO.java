package com.vehicleloan.dao.emiStatus;

import java.util.List;

public interface EMIStatusDAO {
	List<EMIStatus> get(Integer ApplicationId);

	boolean create(EMIStatus emiStatus);

	boolean update(EMIStatus emiStatus);

	boolean delete(Integer statusId);
	
	boolean deleteByApplicationId(Integer applicationId);
}