package com.letv.test.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * BaseTestCase without Transaction
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
@ContextConfiguration(locations = { TestConstants.LOCATIONS })
public abstract class BaseTestCase extends AbstractJUnit4SpringContextTests {

}
