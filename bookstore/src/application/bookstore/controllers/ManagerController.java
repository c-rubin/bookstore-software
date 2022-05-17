package application.bookstore.controllers;

import application.bookstore.views.ManagerView;

public class ManagerController {
	private final ManagerView managerView;
	
	
	
	public ManagerController(ManagerView managerView){
		this.managerView=managerView;
	}
	
	public void setRefreshListener() {
		managerView.getRefreshBtn().setOnAction(e->{
			
			
			String a =ManagerView.getStatistics();
			managerView.getStatisticsLabel().setText(a);
			
			
			
		});
	}
}
