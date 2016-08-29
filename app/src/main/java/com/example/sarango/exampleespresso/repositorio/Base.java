package com.example.sarango.exampleespresso.repositorio;

/**
 * Created by sarango on 29/08/2016.
 */
public class Base<T> {
    private ServiceAdapter serviceAdapter;

    public ServiceAdapter<T> getServiceAdapter() {
        return serviceAdapter;
    }

    public void setServiceAdapter(ServiceAdapter<T> serviceAdapter) {
        this.serviceAdapter = serviceAdapter;
    }

}
