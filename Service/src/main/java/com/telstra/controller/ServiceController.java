package com.telstra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.telstra.entity.ServiceEntity;
import com.telstra.error.RegisteredEndpointsNotFoundException;
import com.telstra.service.service_1;
import com.telstra.repository.*;

@RestController
@RequestMapping("service-api")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {

	@Autowired
	private service_1 ser;

	@Autowired
	ServiceRepository serepo;

	// save api will got to this url
	@PostMapping("/serviceendpoints")
	public List<ServiceEntity> saveservice(@RequestBody List<ServiceEntity> service) {

		return ser.saveservice(service);
	}

	@GetMapping("/serviceendpoints")
	public List<ServiceEntity> fetchregisteredservicesList() {
		return ser.fetchregisteredservicesList();
	}

	@GetMapping("/serviceendpoints-id/{Id}")
	public ServiceEntity fetchRegisteredEndpointsById(@PathVariable("Id") String serviceEndpointsId)
			throws RegisteredEndpointsNotFoundException {
		return ser.fetchRegisteredEndpointsById(serviceEndpointsId);
	}

	// list of registered services we are returning

	// getting optimal service end point
//    @GetMapping("serviceendpoints/{region}")
//    public ServiceEntity fetchoptimalserviceendpoint(@PathVariable("region") String region){
//        return ser.fetchoptimalserviceendpoint(region);
//    }
	@GetMapping("/serviceendpoints-direction/{direction}")
	public List<ServiceEntity> getSongByWeather(@PathVariable("direction") String direction)
			throws RegisteredEndpointsNotFoundException {
		List<ServiceEntity> song = serepo.findByDirection(direction);
		if (song.isEmpty()) {
			throw new RegisteredEndpointsNotFoundException("Song Not Available");
		}

		return song;
		// return songService.fetchSongByWeather(Weather);
	}

	// update by id
	@PutMapping("/serviceendpoints-id/{id}")
	public ServiceEntity updateService(@PathVariable("id") String serviceId, @RequestBody ServiceEntity serviceentity) {
		return ser.updateService(serviceId, serviceentity);
	}

	@DeleteMapping("/serviceendpoints-id/{id}")
	public String deleteServiceById(@PathVariable("id") String serviceId) {
		ser.deleteServiceById(serviceId);
		return "Service Deleted Successfully";
	}

}
