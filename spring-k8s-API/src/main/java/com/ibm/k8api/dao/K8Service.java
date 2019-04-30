package com.ibm.k8api.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class K8Service {
	
	@Id
	private String serviceId;
	private String namespace;
	private String serviceName;
	private String serviceLink;
	private String isMicroservice;
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceLink() {
		return serviceLink;
	}
	public void setServiceLink(String serviceLink) {
		this.serviceLink = serviceLink;
	}
	public String getIsMicroservice() {
		return isMicroservice;
	}
	public void setIsMicroservice(String isMicroservice) {
		this.isMicroservice = isMicroservice;
	}
	

}
