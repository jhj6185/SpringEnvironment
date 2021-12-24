package org.conan.test;

import static org.junit.Assert.assertNotNull;

import org.conan.sample.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RestaurantTest {
	@Setter(onMethod_={@Autowired})
	private Restaurant restaurant;
	
	@Test
	public void testExist() {
		//있는지 없는지 확인
		assertNotNull(restaurant);
		//null인지 확인하기
		log.info(restaurant);
		log.info("---------------------");
		log.info(restaurant.getChef());
	}
}
