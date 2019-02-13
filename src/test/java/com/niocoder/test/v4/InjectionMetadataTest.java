package com.niocoder.test.v4;

import com.niocoder.beans.factory.annotation.AutowiredFieldElement;
import com.niocoder.beans.factory.annotation.InjectionElement;
import com.niocoder.beans.factory.annotation.InjectionMetadata;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.dao.v4.AccountDao;
import com.niocoder.dao.v4.ItemDao;
import com.niocoder.service.v4.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;


public class InjectionMetadataTest {

    @Test
    public void testInjection() throws Exception {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("bean-v4.xml"));

        Class<?> clz = NioCoderService.class;

        LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();

        {
            Field f = NioCoderService.class.getDeclaredField("accountDao");
            InjectionElement injectionElement = new AutowiredFieldElement(f, true, factory);
            elements.add(injectionElement);
        }

        {
            Field f = NioCoderService.class.getDeclaredField("itemDao");
            InjectionElement injectionElement = new AutowiredFieldElement(f, true, factory);
            elements.add(injectionElement);
        }

        InjectionMetadata metadata = new InjectionMetadata(clz, elements);
        NioCoderService nioCoderService = new NioCoderService();
        metadata.inject(nioCoderService);

        Assert.assertTrue(nioCoderService.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(nioCoderService.getItemDao() instanceof ItemDao);
    }
}
