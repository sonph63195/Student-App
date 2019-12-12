package com.duatson.studentapp.application;

import android.content.res.Resources;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.duatson.studentapp.model.Service;
import com.duatson.studentapp.network.ServiceEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreServices {
    private Resources resources;
    private List<Service> services;
    Map<String, Service> servicesMap;

    public StoreServices() {
        servicesMap = new HashMap<>();
    }

    public Resources getResources() {
        return resources;
    }

    public StoreServices setResources(Resources resources) {
        this.resources = resources;
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Map<String, Service> getServicesMap() {
        services = ServiceEntry.initServiceEntryList(getResources());
        for (int i = 0; i < services.size(); i++) {
            Service service = services.get(i);
            servicesMap.put(service.getId(), service);
        }
        return servicesMap;
    }
}
