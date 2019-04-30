package com.ibm.k8api.client;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.util.Config;

@RestController
public class K8sClient {
   @Autowired
   RestTemplate restTemplate;

   @RequestMapping(value = "/k8s/services")
   public String getProductList() {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity <String> entity = new HttpEntity<String>(headers);
      
      //String result = restTemplate.exchange("http://127.0.0.1:8088/api/v1/services", HttpMethod.GET, entity, String.class).getBody();
      
      ResponseEntity<String> result = restTemplate.exchange("http://127.0.0.1:8088/api/v1/services", HttpMethod.GET, entity, String.class);
      
      System.out.println(result);
      return result.toString();
   }
   
   @RequestMapping(value = "/k8s/namespaces")
   public String getAllNamespaces() throws ApiException, IOException {
	   ApiClient client = Config.defaultClient();
       Configuration.setDefaultApiClient(client);
       
       
       CoreV1Api api = new CoreV1Api();
       V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
       V1Service service = new V1Service(); //
       StringBuilder namespaces= new StringBuilder();
       for (V1Pod item : list.getItems()) {
           System.out.println(item.getMetadata().getName());
           //System.out.println(item.getMetadata().getSelfLink());
           //service= api.readNamespacedService("", item.getMetadata().getName(), null, null, null); //
           
           System.out.println(service.getMetadata().getName());//
           //System.out.println("=====================");
           namespaces.append(item.getMetadata().getName() + " ");
       }
       return namespaces.toString();
   }
}
