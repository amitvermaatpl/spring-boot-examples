package com.ibm.k8api.client;

import java.io.IOException;
import java.util.List;

import com.ibm.k8api.dao.K8Service;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.apis.ExtensionsV1beta1Api;
import io.kubernetes.client.models.V1APIResourceList;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.models.V1ServiceList;
import io.kubernetes.client.util.Config;

public class Demo {

	public static void main(String[] args) throws IOException {
		////ApiClient defaultClient = Configuration.getDefaultApiClient();

		// Configure API key authorization: BearerToken
		////ApiKeyAuth BearerToken = (ApiKeyAuth) defaultClient.getAuthentication("BearerToken");
		////BearerToken.setApiKey("YOUR API KEY");
		// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
		//BearerToken.setApiKeyPrefix("Token");
		ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        
        System.out.println("==> "+client.getBasePath().substring(8, 23) ); 
		ExtensionsV1beta1Api apiInstance = new ExtensionsV1beta1Api();
		try {
		    V1APIResourceList result = apiInstance.getAPIResources();
		    System.out.println(result);
		    
		    ////////////////////////////////////////////////////////
		    CoreV1Api api = new CoreV1Api();
		 // FETCH the services from all pods //
		       V1ServiceList svcList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null);
		       
		       List<V1Service> services=svcList.getItems();
		       for(V1Service svc: services) {
		       	System.out.println("Goint to store the service: "+svc.getMetadata().getName());
		       	
		        // STORE the Service in DB //
		       	K8Service service = new K8Service();	
		       	service.setIsMicroservice("false"); // TODO: Need to be changed //
		       	service.setNamespace(svc.getMetadata().getNamespace());
		       	service.setServiceName(svc.getMetadata().getName());
		       	service.setServiceLink(svc.getMetadata().getSelfLink());
				//serviceRepository.save(service);
				
		       	System.out.println("================"+svc.getSpec().getPorts().get(0).getNodePort());
		       	String svcUrl= "http://"+ client.getBasePath().substring(8, 23) + svc.getSpec().getPorts().get(0).getNodePort();
		       	System.out.println("################## "+svcUrl);
		       }
		    
		    ////////////////////////////////////////////////////////
		} catch (ApiException e) {
		    System.err.println("Exception when calling ExtensionsV1beta1Api#getAPIResources");
		    e.printStackTrace();
		}

	}

}
