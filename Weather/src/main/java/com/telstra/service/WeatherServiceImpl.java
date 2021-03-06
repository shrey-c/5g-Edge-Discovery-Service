package com.telstra.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telstra.entity.Weather;
import com.telstra.error.WeatherNotFoundException;
import com.telstra.repository.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService{

	@Autowired
    private WeatherRepository weatherRepository;
	
	@Override
    public List<Weather> saveWeather (List<Weather> weather) {
        return weatherRepository.saveAll(weather);
    }
	
	@Override
	public List<Weather> fetchWeatherList(){
        return weatherRepository.findAll();
    }
	
	//throws WeatherNotFoundException 
	@Override
    public Weather fetchWeatherById(Long Id){
       // Optional<Weather> weather = weatherRepository.findById(Id);

//        if(!weather.isPresent())
//        {
//            throw new WeatherNotFoundException("Weather Not Available");
//        }
		
        //return weather.get();
		return weatherRepository.findById(Id).get();
    }
	
	@Override
	public Weather updateWeather(Long weatherId, Weather weather) {
		Weather weaDB = weatherRepository.findById(weatherId).get();
		
		if(Objects.nonNull(weather.getCity()) &&
              !"".equalsIgnoreCase(weather.getCity())){
          weaDB.setCity(weather.getCity());
      }
		
		if(Objects.nonNull(weather.getWeather()))
		{    weaDB.setWeather(weather.getWeather());
	    }
				
		return weatherRepository.save(weaDB);
	}

	@Override
	public void deleteWeatherById(Long weatherId) {
		// TODO Auto-generated method stub
		 weatherRepository.deleteById(weatherId);
	}

}
