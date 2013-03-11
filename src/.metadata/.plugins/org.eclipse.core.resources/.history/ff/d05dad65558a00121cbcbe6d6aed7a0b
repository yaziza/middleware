package de.tud.in.middleware.snapshot;

import java.io.Serializable;
import java.util.Random;


public final class EmployeeClientProxy implements Serializable,  MobileClient{
	private static final long serialVersionUID = -6014457740219416126L;
	
	public final Integer id = new Random().nextInt();
	
	private MobileManagementRemote mobileManagment;
	
	public EmployeeClientProxy(){
		
	}

	@Override
	public boolean prepareToUpdateSnapshot(Snapshot snap) {
		return false;
	}

	@Override
	public boolean updateToSnapshot(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
