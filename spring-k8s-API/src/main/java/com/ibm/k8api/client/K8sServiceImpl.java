package com.ibm.k8api.client;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.k8api.dao.K8Service;
import com.ibm.k8api.dao.K8ServiceRepository;

import io.kubernetes.client.ApiException;
import io.kubernetes.client.models.V1Service;

@RestController
public class K8sServiceImpl {
   @Autowired
   RestTemplate restTemplate;
   
   @Autowired
   private final K8ServiceRepository serviceRepository;
   
   @Autowired
   private K8sFacade kubeFacade;
   
   @Autowired
   public K8sServiceImpl(K8ServiceRepository serviceRepository) {
		this.serviceRepository= serviceRepository;
   }
 
   @RequestMapping(value = "/k8s/apistore")
   public void storeAllK8Services() throws ApiException, IOException {
	   	      
       List<V1Service> services=kubeFacade.fetchKubernetesServices();
       
       for(V1Service svc: services) {	       		       	
	        // STORE the service-details in DB //
	       	K8Service service = getServiceObject(svc,kubeFacade.getMINIKUBE_IP());	
	       	
	       	if(service.getServiceLink()!=null) {
	       		System.out.println("Going to store the service url " + service.getServiceLink());
	       		//boolean isReachable= checkServiceAvailability(service);
	       		serviceRepository.save(service);
	       	}
       }       
       System.out.println("Task completed, all k8s services are stored in databse."); 
   }


	private K8Service getServiceObject(V1Service svc, String minikubeIp) {
		Integer nodePort=svc.getSpec().getPorts().get(0).getNodePort();
		K8Service service = new K8Service();
		String serviceUrl="";
		
		if(nodePort!=null) {
			// *** WE need to store SWAGGER URL Only ***// LOGIC need to be changed //
			serviceUrl=minikubeIp+nodePort+"/demo/swagger-ui.html#";  
			
			service.setIsMicroservice("false"); // TODO: Need to be changed //
	       	service.setNamespace(svc.getMetadata().getNamespace());
	       	service.setServiceName(svc.getMetadata().getName());
	       	if(kubeFacade.isServiceReachable(serviceUrl)) {			// B4 setting the serviceURL check it's reachable or not
	       		service.setServiceLink(serviceUrl); 
	       	}
		}
		return service;
	}
}
