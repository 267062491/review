package com.letv.tbtSps.common.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-14
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class DefaultFormatterRegistrar implements FormatterRegistrar {

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.format.FormatterRegistrar#registerFormatters(org.
     * springframework.format.FormatterRegistry)
     */
    public void registerFormatters(FormatterRegistry registry) {
        if (registry instanceof ConversionService) {
            ConversionService service = (ConversionService) registry;
            registry.addConverterFactory(new StringToEnumConverterFactory(service));
        }
        // register custom converter.
    }

}