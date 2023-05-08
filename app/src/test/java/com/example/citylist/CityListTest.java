package com.example.citylist;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityListTest {
    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "AB");
    }

    @Test
    public void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());

        City city = new City("Regina", "SK");
        cityList.add(city);

        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    @Test
    public void testdelete(){
        CityList cityList=new CityList();
        City city1=new City("Joypurhat","Rajshahi");
        City city2=new City("Jashore","Khulna");
        cityList.add(city1);
        cityList.add(city2);

        cityList.delete(city1);
        assertTrue(!cityList.getCities().contains(city1));
    }

    @Test
    public void testAddException() {
        CityList cityList = new CityList();
        City city = mockCity();
        cityList.add(city);

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }
    @Test
    public void testdeleteexception() {
        CityList cityList=new CityList();
        City city1=new City("Joypurhat","Rajshahi");
        City city2=new City("Jashore","Khulna");
        cityList.add(city1);
        cityList.add(city2);

        cityList.delete(city1);
        assertThrows(IllegalArgumentException.class,()->{
            cityList.delete(city1);
        });
    }


    @Test
    public void testCount() {
        CityList cityList=new CityList();
        City city1=new City("Joypurhat","Rajshahi");
        City city2=new City("Jashore","Khulna");
        cityList.add(city1);
        cityList.add(city2);

        cityList.delete(city1);
        assertEquals(1,cityList.Count());
    }
    @Test
    public void testGetCities() {
        CityList cityList = mockCityList();
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    @Test
    public void testSort(){
        CityList cityList = new CityList();

        cityList.add(new City("Pabna","Aahshahi"));
        cityList.add(new City("Kushtia","Khulna"));

        cityList.add(new City("Magura","Khulna"));
        cityList.add(new City("Bogra","Rahshahi"));

        cityList.add(new City("Meherpur","Khulna"));


        //sort by city name
        List<City> sortedList = cityList.getCities();
        assertTrue(isSorted(cityList.getOrigCity(),sortedList,true));  //getOrigCity() is called for private var



        //sort by province name
        List<City> list = cityList.getCities(true);
        assertTrue(isSorted(cityList.getOrigCity(),list,false));
    }

    private boolean isSorted(List<City> oldList,List<City> newList, boolean byName){
        if(oldList.size() != newList.size()) return false;

        if(byName) Collections.sort(oldList);
        else oldList.sort(Comparator.comparing(City::getProvinceName));

        for(int i=0; i<oldList.size(); i++){
            if(!oldList.get(i).allEquals(newList.get(i))) return false;
        }
        return true;
    }

}
