package com.ibm.k8api.client;

import java.io.IOException;
import java.util.List;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.models.V1ServiceList;
import io.kubernetes.client.util.Config;

public class K8Namespaces {
    public static void main(String[] args) throws IOException, ApiException{
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        
        
        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        V1Service service = new V1Service(); //
        for (V1Pod item : list.getItems()) {
            //System.out.println(item.getMetadata().getName());
            //System.out.println(item.getMetadata().getSelfLink());
            service= api.readNamespacedService("", item.getMetadata().getName(), null, null, null); //
            
            //System.out.println(service.getMetadata().getName());//
            //System.out.println("=====================");
        }
        
        // FETCH the services from all pods //
        V1ServiceList svcList = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null);
        
        List<V1Service> services=svcList.getItems();
        for(V1Service svc: services) {
        	System.out.println(svc.getMetadata().getName());
        }
    }
}
