
package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.zerock.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class SampleTests {
  //의존성 주입 - Setter 주입 방법
  @Setter(onMethod_ = { @Autowired })
  private Restaurant restaurant;  

  @Test
  public void testExist() {
    
    assertNotNull(restaurant);
    
    log.info(restaurant);
    log.info("----------------------------------");
    log.info(restaurant.getChef());
    
  }
  
  
}
