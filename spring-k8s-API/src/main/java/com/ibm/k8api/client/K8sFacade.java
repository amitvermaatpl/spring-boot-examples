package com.ibm.k8api.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.models.V1ServiceList;
import io.kubernetes.client.util.Config;

@Component
public class K8sFacade {
	private String MINIKUBE_IP;
	public List<V1Service> fetchKubernetesServices() throws IOException, ApiException{
		//*** K8s API Client ***//	   
		   ApiClient client = Config.defaultClient();
	       Configuration.setDefaultApiClient(client);
	              
	       MINIKUBE_IP= "http://"+client.getBasePath().substring(8, 23);       
	       
	       // FETCH the SERVICES from all NAMESPACES //
	       CoreV1Api api = new CoreV1Api();
	       V1ServiceList svcList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null);
	       return svcList.getItems();
	}
	public boolean isServiceReachable(String serviceUrl) {	
		//serviceUrl= serviceUrl+"/demo";
		//serviceUrl= serviceUrl+"/demo/swagger-ui.html#";		
		boolean isReachable=false;
		try {
			URL siteURL = new URL(serviceUrl);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();
 
			int code = connection.getResponseCode();
			if(code==200) {
				System.out.println(serviceUrl+ " service is reachable");
				isReachable= true;
			}else {
				System.out.println("Response-Code: "+code);
			}
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			isReachable= false;
		}
		return isReachable;		
	}
	public String getMINIKUBE_IP() {
		return MINIKUBE_IP;
	}	
}
